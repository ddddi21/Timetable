package com.technokratos.auth.presentation.auth

import android.util.Log
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.domain.model.StudentItem
import com.technokratos.auth.presentation.mapper.mapStudentItemToElectiveItemModel
import com.technokratos.auth.presentation.mapper.mapStudentItemToStudentItemModel
import com.technokratos.auth.presentation.model.ElectiveItemModel
import com.technokratos.auth.presentation.model.ScreenType.*
import com.technokratos.auth.presentation.model.StudentItemModel
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType
import com.technokratos.common.resources.ResourceManager
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AuthViewModel(
    private val router: AuthRouter,
    private val interactor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _studentChooseState =
        MutableSharedFlow<StudentChooseState>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val studentChooseState = _studentChooseState.asSharedFlow()

    private val _listFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val listFlow = _listFlow.asSharedFlow()

    private var selectedItemIdList = mutableListOf<Int>()

    init {
        setUniversityScreen()
        setList()
    }

    fun onNextButtonClick() {
        setUpCurrentList(setUpCurrentStateAfterNextButtonClick())
    }

    fun onBackClick() {
        setUpCurrentList(setUpStateAfterBackClick())
    }

    private fun setList() { // TODO(delete later)
        doCoroutineWork {
            _listFlow.emit(
                getUniversity().map {
                    mapStudentItemToStudentItemModel(it) {
                        onItemChecked(it)
                    }
                }
            )
        }
    }

    private fun onItemChecked(item: StudentItemModel) {
        val oldList = _listFlow.replayCache.first()

        doCoroutineWork {
            _listFlow.emit(oldList.map { viewType ->

                val modelViewType = viewType as StudentItemModel

                if (modelViewType.isChecked && modelViewType.id != item.id) {
                    modelViewType.isChecked = false
                } else if (modelViewType.id == item.id) {
                    modelViewType.isChecked = !modelViewType.isChecked

                    if (modelViewType.isChecked) {
                        selectedItemIdList.add(item.id)
                    } else selectedItemIdList.clear()

                }
                viewType
            }
            )

            setCheckedScreenState()
        }
    }

    private fun onElectiveClicked(item: ElectiveItemModel) {
        val currentState = _studentChooseState.replayCache.first()

        selectedItemIdList.add(item.id)

        currentState.screenType = ELECTIVE_DETAIL
        currentState.selectedElectiveId = selectedItemIdList.first()

        setUpCurrentList(currentState)
    }

    private fun setCheckedScreenState() {
        val oldStudentChooseState = _studentChooseState.replayCache.first()

        doCoroutineWork {
            _studentChooseState.emit(
                oldStudentChooseState.copy(
                    isItemChosen = !selectedItemIdList.isNullOrEmpty()
                )
            )
        }
    }

    private fun setUniversityScreen() {
        doCoroutineWork {
            _studentChooseState.emit(StudentChooseState(screenType = UNIVERSITY))
        }
    }


    private fun setUpCurrentList(newState: StudentChooseState) {
        doCoroutineWork {
            // back request
            // make Result<Unit>
            val result: List<StudentItem> = when (newState.screenType) {
                UNIVERSITY -> {
                    getUniversity()
                }
                INSTITUTE -> {
                    getInstitute()
                }
                GROUP -> {
                    getGroup()
                }
                ELECTIVES -> {

                    newState.isItemChosen = true

                    getElectives()
                }
                ELECTIVE_DETAIL -> {
                    getElectiveDetails()
                }
            }

            _studentChooseState.emit(newState)

            _listFlow.emit(
                result.map {
                    if (newState.screenType == ELECTIVES) {
                        mapStudentItemToElectiveItemModel(it) {
                            onElectiveClicked(it)
                        }
                    } else {
                        mapStudentItemToStudentItemModel(it) {
                            onItemChecked(it)
                        }
                    }
                }
            )
            result.forEach {
                if (newState.screenType == ELECTIVE_DETAIL &&
                    newState.selectedElectivesList.contains(it.id)
                ) {
                    onItemChecked(mapStudentItemToStudentItemModel(it))
                }
            }
        }
    }

    private fun setUpCurrentStateAfterNextButtonClick(): StudentChooseState {
        val currentState = _studentChooseState.replayCache.first()

        when (currentState.screenType) {
            UNIVERSITY -> {
                currentState.screenType = INSTITUTE
                currentState.selectedUniversityId = selectedItemIdList.first()
            }
            INSTITUTE -> {
                currentState.screenType = GROUP
                currentState.selectedInstituteId = selectedItemIdList.first()
            }
            GROUP -> {
                currentState.screenType = ELECTIVES
                currentState.selectedGroupId = selectedItemIdList.first()
            }
            ELECTIVES -> {
                //router.navigateToMain
            }
            ELECTIVE_DETAIL -> {
                selectedItemIdList.removeAt(0)
                currentState.selectedElectivesList.addAll(selectedItemIdList)
                currentState.screenType = ELECTIVES
            }
        }
        currentState.isNeedToShowBackArrow = true
        currentState.isItemChosen = false

        selectedItemIdList.clear()




        return currentState
    }

    private fun setUpStateAfterBackClick(): StudentChooseState {
        val currentState = _studentChooseState.replayCache.first()

        when (currentState.screenType) {
            INSTITUTE -> {
                currentState.screenType = UNIVERSITY
                currentState.isNeedToShowBackArrow = false
                currentState.selectedInstituteId = 0
                currentState.selectedUniversityId = 0
            }
            GROUP -> {
                currentState.screenType = INSTITUTE
                currentState.selectedGroupId = 0
                currentState.selectedInstituteId = 0
            }
            ELECTIVES -> {
                currentState.screenType = GROUP
                currentState.selectedGroupId = 0
                currentState.selectedElectivesList.clear()
            }
            ELECTIVE_DETAIL -> {
                currentState.screenType = ELECTIVES
                currentState.isItemChosen = true
                currentState.selectedElectiveId = 0
            }
            else -> {
            }
        }
        selectedItemIdList.clear()


        return currentState
    }
}
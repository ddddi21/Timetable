package com.technokratos.auth.presentation.auth

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

    private fun setList() {
        doCoroutineWork {
            val result = interactor.getUniversity().map {
                it.map { university ->
                    StudentItem(
                        id = university.id,
                        title = university.name
                    )
                }
            }

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _listFlow.emit(
                        it.map {
                            mapStudentItemToStudentItemModel(it) {
                                onItemChecked(it)
                            }
                        }
                    )
                }
            }
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
            val result: Result<List<StudentItem>> = when (newState.screenType) {
                UNIVERSITY -> {
                    interactor.getUniversity().map {
                        it.map { university ->
                            StudentItem(
                                id = university.id,
                                title = university.name
                            )
                        }
                    }
                }
                INSTITUTE -> {
                    interactor.getInstitute(_studentChooseState.replayCache.first().selectedUniversityId.toString())
                        .map {
                            it.map { institute ->
                                StudentItem(
                                    id = institute.id,
                                    title = institute.name
                                )
                            }
                        }
                }
                GROUP -> {
                    interactor.getGroup(_studentChooseState.replayCache.first().selectedInstituteId.toString())
                        .map {
                            it.map { group ->
                                StudentItem(
                                    id = group.id,
                                    title = group.groupNumber
                                )
                            }
                        }
                }
                ELECTIVES -> {

                    newState.isItemChosen = true

                    interactor.getCourseBlock(_studentChooseState.replayCache.first().selectedGroupId.toString())
                        .map {
                            it.map { block ->
                                StudentItem(
                                    id = block.id,
                                    title = block.name
                                )
                            }
                        }
                }
                ELECTIVE_DETAIL -> {
                    interactor.getCourse(_studentChooseState.replayCache.first().selectedElectiveId.toString())
                        .map {
                            it.map { course ->
                                StudentItem(
                                    id = course.id,
                                    title = course.name
                                )
                            }
                        }
                }
            }

            _studentChooseState.emit(newState)

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _listFlow.emit(
                        it.map {
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
                    it.forEach { item ->
                        if (newState.screenType == ELECTIVE_DETAIL &&
                            newState.selectedElectivesList.contains(item.id)
                        ) {
                            onItemChecked(mapStudentItemToStudentItemModel(item))
                        }
                    }
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

    fun onActionButtonClicked() {
        interactor.saveUserSettings(studentChooseState.replayCache.first())
        router.navigateToMain(studentChooseState.replayCache.first())
    }
}
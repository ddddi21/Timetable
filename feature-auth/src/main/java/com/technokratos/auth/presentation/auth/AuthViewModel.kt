package com.technokratos.auth.presentation.auth

import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.domain.model.University
import com.technokratos.auth.presentation.mapper.mapUniversityToUniversityItemModel
import com.technokratos.auth.presentation.model.ScreenType
import com.technokratos.auth.presentation.model.StudentItemModel
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType
import com.technokratos.common.resources.ResourceManager
import com.technokratos.common.utils.isNullOrZero
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

    private var selectedItemId = 0

    private val list = listOf( // TODO(remove)
        University(1, "Кис"),
        University(2, "Кис"),
        University(3, "Я"),
        University(4, "Котик"),
        University(5, "Ты"),
        University(6, "Котик"),
        University(7, "А"),
        University(8, "Твои"),
        University(9, "Поцелуи"),
        University(10, "Почти"),
        University(11, "Как"),
        University(12, "Легкий"),
        University(13, "Цензура"),
        University(15, "..")
    )

    init {
        setUniversityScreen()
        setList()
    }

    private fun setList() { // TODO(delete later)
        doCoroutineWork {
            _listFlow.emit(
                list.map {
                    mapUniversityToUniversityItemModel(it) {
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

                    selectedItemId = if (modelViewType.isChecked) item.id else 0
                }
                viewType
            }
            )

            setCheckedScreenState()
        }
    }

    private fun setCheckedScreenState() {
        val oldStudentChooseState = _studentChooseState.replayCache.first()

        doCoroutineWork {
            _studentChooseState.emit(
                oldStudentChooseState.copy(
                    isItemChosen = !selectedItemId.isNullOrZero()
                )
            )
        }
    }

    private fun setUniversityScreen() {
        doCoroutineWork {
            _studentChooseState.emit(StudentChooseState(screenType = ScreenType.UNIVERSITY))
        }
    }

    private fun setInstituteScreen() {
        doCoroutineWork {
            _studentChooseState.emit(
                StudentChooseState(
                    isNeedToShowBackArrow = true,
                    screenType = ScreenType.INSTITUTE
                )
            )
        }
    }

    private fun setGroupScreen() {
        doCoroutineWork {
            _studentChooseState.emit(
                StudentChooseState(
                    isNeedToShowBackArrow = true,
                    screenType = ScreenType.GROUP
                )
            )
        }
    }
}
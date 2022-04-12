package com.technokratos.auth.presentation.auth

import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.presentation.mapper.mapTitleToUniversityItemModel
import com.technokratos.auth.presentation.model.ScreenType
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

    private val list = listOf( // TODO(remove)
        "Кис",
        "Кис",
        "Я",
        "Котик",
        "Ты",
        "Котик",
        "А",
        "Твои",
        "Поцелуй",
        "Почти",
        "Как",
        "Легкий",
        "Цензура",
        ".."
    )

    init {
        setUniversityScreen()
        setList()
    }

    private fun setList() { // TODO(delete later)
        doCoroutineWork {
            _listFlow.emit(
                list.map {
                    mapTitleToUniversityItemModel(it) {
                        router.navigateToRegistration() // TODO (rewrite to radio button logic)
                    }
                }
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
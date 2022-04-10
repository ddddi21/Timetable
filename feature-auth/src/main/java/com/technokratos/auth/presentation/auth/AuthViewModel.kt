package com.technokratos.auth.presentation.auth

import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.presentation.model.ScreenType
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.resources.ResourceManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class AuthViewModel(
    private val router: AuthRouter,
    private val interactor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _studentChooseState = MutableSharedFlow<StudentChooseState>()
    val studentChooseState = _studentChooseState.asSharedFlow()

    init {
        setUniversityScreen()
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
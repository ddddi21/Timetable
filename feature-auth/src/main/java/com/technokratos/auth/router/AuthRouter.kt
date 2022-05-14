package com.technokratos.auth.router

import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.router.NavigateBackRouter

interface AuthRouter : NavigateBackRouter {

    fun navigateToMain(chooseState: StudentChooseState)
}
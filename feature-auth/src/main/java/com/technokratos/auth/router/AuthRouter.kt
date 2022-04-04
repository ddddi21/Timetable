package com.technokratos.auth.router

import com.technokratos.common.router.NavigateBackRouter

interface AuthRouter : NavigateBackRouter {

    fun navigateToMain()

    fun navigateToRegistration()
}
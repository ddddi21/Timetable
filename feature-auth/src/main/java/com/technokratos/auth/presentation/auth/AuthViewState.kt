package com.technokratos.auth.presentation.auth

data class AuthViewState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val isLoginButtonEnabled: Boolean = false,
    val isLoginErrorMessageVisible: Boolean = false,
    val isNeedToShowProgress: Boolean = false,
    val loginErrorMessage: String = "",
    val isSamePassword: Boolean = true
)
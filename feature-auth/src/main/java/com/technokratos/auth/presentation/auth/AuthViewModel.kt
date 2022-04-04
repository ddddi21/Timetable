package com.technokratos.auth.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.R
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.resources.ResourceManager
import kotlinx.coroutines.launch

class AuthViewModel(
    private val router: AuthRouter,
    private val interactor: AuthInteractor,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private var _authViewState = MutableLiveData<AuthViewState>()
    var authViewState: LiveData<AuthViewState> = _authViewState

    init {
        _authViewState.value = AuthViewState()
    }

    fun onRegistrationClicked() {
        router.navigateToRegistration()
    }

    fun onLoginButtonClicked(email: String, password: String) {
        viewModelScope.launch {
            _authViewState.value = AuthViewState(email, password, isNeedToShowProgress = true)
            interactor.signIn(email, password).run {
                if (isSuccess) {
                    onSuccessfulLogin(email, password)
                } else {
                    onFailedLoginError(email, password, exceptionOrNull()!!)
                }
            }
        }
    }

    private fun onSuccessfulLogin(email: String, password: String) {
        _authViewState.value = AuthViewState(email, password)
        router.navigateToMain()
    }

    private fun onFailedLoginError(email: String, password: String, throwable: Throwable) {
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = false,
            isLoginErrorMessageVisible = true,
            loginErrorMessage = resourceManager.getString(R.string.login_error_message))
    }

    fun onTextChanged(email: String, password: String) {
        _authViewState.value = AuthViewState(
            email,
            password,
            isLoginButtonEnabled = email.isNotEmpty() && password.isNotEmpty()
        )
    }
}
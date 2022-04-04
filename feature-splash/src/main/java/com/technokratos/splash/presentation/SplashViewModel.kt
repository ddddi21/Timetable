package com.technokratos.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.utils.Event
import com.technokratos.splash.SplashRouter
import com.technokratos.splash.domain.SplashInteractor
import kotlinx.coroutines.launch

class SplashViewModel(
    private val router: SplashRouter,
    private val splashInteractor: SplashInteractor
) : BaseViewModel() {

    private val _openUsersEvent = MutableLiveData<Event<Unit>>()
    val openUsersEvent: LiveData<Event<Unit>> = _openUsersEvent

    init {
        _openUsersEvent.value = Event(Unit)
    }

    fun onAnimationFinished() {
        viewModelScope.launch {
//            if (!splashInteractor.isUserLoggedIn()) {
//                router.toLogin()
//            } else {
//                router.navigateToMain()
//            }
            router.toLogin()
        }
    }
}
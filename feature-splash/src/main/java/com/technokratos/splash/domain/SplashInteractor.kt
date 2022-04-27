package com.technokratos.splash.domain

import com.technokratos.common.local.sp.UserSharedPreferences
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val userSharedPreferences: UserSharedPreferences
) {
    fun isUserLoggedIn(): Boolean {
        return true
    }

    fun getUserRefreshToken(): String? {
        return  ""
    }
}
package com.technokratos.common.local.sp

interface UserSharedPreferences {

    var userAuthToken: String?
    var userRefreshToken: String?

    fun clear()
}
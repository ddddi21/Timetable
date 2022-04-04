package com.technokratos.common.local.sp

import android.content.SharedPreferences
import javax.inject.Inject

private const val KEY_USER_AUTH_TOKEN = "userAuthToken"
private const val KEY_USER_REFRESH_TOKEN = "userRefreshToken"

class UserSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : UserSharedPreferences {

    override var userAuthToken: String?
        get() {
            return sharedPreferences.getString(KEY_USER_AUTH_TOKEN, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_AUTH_TOKEN, value).apply()

    override var userRefreshToken: String?
        get() {
            return sharedPreferences.getString(KEY_USER_REFRESH_TOKEN, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_REFRESH_TOKEN, value).apply()

    override fun clear() {
        userAuthToken = null
        userRefreshToken = null
    }
}
package com.technokratos.common.local.sp

import android.content.SharedPreferences
import javax.inject.Inject

private const val KEY_USER_UNIVERSITY = "university"
private const val KEY_USER_INSTITUTE = "institute"
private const val KEY_USER_GROUP= "group"
private const val KEY_USER_BLOCK = "block"
private const val KEY_USER_COURSES = "courses"

class UserSharedPreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : UserSharedPreferences {

    override var university: String?
        get() {
            return sharedPreferences.getString(KEY_USER_UNIVERSITY, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_UNIVERSITY, value).apply()

    override var institute: String?
        get() {
            return sharedPreferences.getString(KEY_USER_INSTITUTE, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_INSTITUTE, value).apply()

    override var group: String?
        get() {
            return sharedPreferences.getString(KEY_USER_GROUP, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_GROUP, value).apply()

    override var block: String?
        get() {
            return sharedPreferences.getString(KEY_USER_BLOCK, null)
        }
        set(value) = sharedPreferences.edit().putString(KEY_USER_BLOCK, value).apply()

    override var courses: MutableSet<String>?
        get() {
            return sharedPreferences.getStringSet(KEY_USER_COURSES, null)
        }
        set(value) = sharedPreferences.edit().putStringSet(KEY_USER_COURSES, value).apply()


    override fun clear() {
        university = null
        institute = null
        group = null
        block = null
        courses = null
    }
}
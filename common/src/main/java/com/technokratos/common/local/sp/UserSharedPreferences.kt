package com.technokratos.common.local.sp

interface UserSharedPreferences {

    var university: String?
    var institute: String?
    var group: String?
    var block: String?
    var courses: MutableSet<String>?

    fun clear()
}
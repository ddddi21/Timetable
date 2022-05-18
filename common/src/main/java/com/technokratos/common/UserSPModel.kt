package com.technokratos.common

data class UserSPModel(
    var university: String?,
    var institute: String?,
    var group: String?,
    var block: String?,
    var courses: MutableSet<String>?
)
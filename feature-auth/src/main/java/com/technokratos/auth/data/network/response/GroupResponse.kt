package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class GroupResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("group_number")
    val groupNumber: String,
    @SerializedName("course")
    val course: Int
)
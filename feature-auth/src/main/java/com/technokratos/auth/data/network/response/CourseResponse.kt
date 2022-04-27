package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("block")
    val block: Int
)
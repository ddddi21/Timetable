package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class BlockResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("course")
    val course: Int
)
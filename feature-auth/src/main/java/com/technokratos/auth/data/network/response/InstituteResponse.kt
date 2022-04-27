package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class InstituteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("short_name")
    val shortName: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("university")
    val university: Int
)
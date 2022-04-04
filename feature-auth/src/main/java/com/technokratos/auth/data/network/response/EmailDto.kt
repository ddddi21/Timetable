package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class EmailDto(
    @SerializedName("email")
    val email: String
)
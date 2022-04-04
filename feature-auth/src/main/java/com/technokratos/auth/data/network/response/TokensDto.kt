package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class TokensDto(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String
)
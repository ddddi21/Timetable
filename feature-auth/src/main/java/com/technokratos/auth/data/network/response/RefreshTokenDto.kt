package com.technokratos.auth.data.network.response

import com.google.gson.annotations.SerializedName

data class RefreshTokenDto(
    @SerializedName("refreshToken") // TODO(спросить такой ли, тк в сваггере не написано)
    val refreshToken: String
)
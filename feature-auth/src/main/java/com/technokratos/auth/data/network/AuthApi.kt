package com.technokratos.auth.data.network

import com.technokratos.auth.data.network.response.AuthDto
import com.technokratos.auth.data.network.response.EmailDto
import com.technokratos.auth.data.network.response.RefreshTokenDto
import com.technokratos.auth.data.network.response.TokensDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users")
    suspend fun signUp(@Body singUpBody: AuthDto)

    @POST("auth/login")
    suspend fun signIn(@Body singInBody: AuthDto): TokensDto

    @POST("auth/token")
    suspend fun updateTokens(@Body refreshToken: RefreshTokenDto): TokensDto

    @POST("auth/reset")
    suspend fun resetPassword(@Body emailBody: EmailDto)
}
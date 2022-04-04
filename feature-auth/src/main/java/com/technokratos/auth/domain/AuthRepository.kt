package com.technokratos.auth.domain

interface AuthRepository {

    suspend fun signUp(email: String, password: String)

    suspend fun signIn(email: String, password: String)

    suspend fun reset(email: String)

    suspend fun updateTokens(refreshToken: String)
}
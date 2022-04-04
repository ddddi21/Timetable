package com.technokratos.auth.domain

import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun signUp(email: String, password: String): Result<Unit> = runCatching {
        authRepository.signUp(email, password)
    }

    suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
        authRepository.signIn(email, password)
    }

    suspend fun resetPassword(email: String): Result<Unit> = runCatching {
        authRepository.reset(email)
    }

    suspend fun updateTokens(refreshToken: String): Result<Unit> = runCatching {
        authRepository.updateTokens(refreshToken)
    }
}
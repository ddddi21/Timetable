package com.technokratos.auth.domain

import com.technokratos.auth.data.network.model.Block
import com.technokratos.auth.data.network.model.Course
import com.technokratos.auth.data.network.model.Group
import com.technokratos.auth.data.network.model.Institute
import com.technokratos.auth.data.network.model.University
import com.technokratos.auth.presentation.state.StudentChooseState
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun getUniversity(): Result<List<University>> = runCatching {
        authRepository.getUniversity()
    }

    suspend fun getInstitute(id: String): Result<List<Institute>> = runCatching {
        authRepository.getInstitute(id)
    }

    suspend fun getGroup(id: String): Result<List<Group>> = runCatching {
        authRepository.getGroup(id)
    }

    suspend fun getCourseBlock(id: String): Result<List<Block>> = runCatching {
        authRepository.getCourseBlock(id)
    }

    suspend fun getCourse(id: String): Result<List<Course>> = runCatching {
        authRepository.getCourse(id)
    }

    fun saveUserSettings(studentChooseState: StudentChooseState) =
        authRepository.saveUserSettings(studentChooseState)
}
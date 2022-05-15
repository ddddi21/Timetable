package com.technokratos.auth.domain

import com.technokratos.auth.data.network.model.Block
import com.technokratos.auth.data.network.model.Course
import com.technokratos.auth.data.network.model.Group
import com.technokratos.auth.data.network.model.Institute
import com.technokratos.auth.data.network.model.University
import com.technokratos.auth.presentation.state.StudentChooseState

interface AuthRepository {

    suspend fun getUniversity(): List<University>

    suspend fun getInstitute(id: String): List<Institute>

    suspend fun getGroup(id: String): List<Group>

    suspend fun getCourseBlock(id: String): List<Block>

    suspend fun getCourse(id: String): List<Course>

    fun saveUserSettings(studentChooseState: StudentChooseState)
}
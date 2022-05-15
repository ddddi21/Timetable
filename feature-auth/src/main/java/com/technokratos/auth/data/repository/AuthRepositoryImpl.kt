package com.technokratos.auth.data.repository

import com.technokratos.auth.data.network.AuthApi
import com.technokratos.auth.data.network.model.Block
import com.technokratos.auth.data.network.model.Course
import com.technokratos.auth.data.network.model.Group
import com.technokratos.auth.data.network.model.Institute
import com.technokratos.auth.data.network.model.University
import com.technokratos.auth.domain.AuthRepository
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.local.sp.UserSharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userSharedPreferences: UserSharedPreferences
) : AuthRepository {

    override suspend fun getUniversity(): List<University> {
        return withContext(Dispatchers.IO) {
            authApi.getUniversity().map { response ->
                University(
                    id = response.id,
                    name = response.name,
                    shortName = response.shortName,
                    link = response.link
                )
            }
        }
    }

    override suspend fun getInstitute(id: String): List<Institute> {
        return withContext(Dispatchers.IO) {
            authApi.getInstitute(id).map { response ->
                Institute(
                    id = response.id,
                    name = response.name,
                    shortName = response.shortName,
                    link = response.link,
                    universityId = response.university
                )
            }
        }
    }

    override suspend fun getGroup(id: String): List<Group> {
        return withContext(Dispatchers.IO) {
            authApi.getGroup(id).map { response ->
                Group(
                    id = response.id,
                    groupNumber = response.groupNumber,
                    courseId = response.course,
                )
            }
        }
    }

    override suspend fun getCourseBlock(id: String): List<Block> {
        return withContext(Dispatchers.IO) {
            authApi.getCourseBlock(id).map { response ->
                Block(
                    id = response.id,
                    name = response.name,
                    courseId = response.course
                )
            }
        }
    }

    override suspend fun getCourse(id: String): List<Course> {
        return withContext(Dispatchers.IO) {
            authApi.getCourse(id).map { response ->
                Course(
                    id = response.id,
                    name = response.name,
                    blockId = response.block
                )
            }
        }
    }

    override fun saveUserSettings(studentChooseState: StudentChooseState) {
        with(userSharedPreferences) {
            university = studentChooseState.selectedUniversityId.toString()
            institute = studentChooseState.selectedInstituteId.toString()
            group = studentChooseState.selectedGroupId.toString()
            block = studentChooseState.selectedElectiveId.toString().ifEmpty {
                null
            }
            courses =
                if (studentChooseState.selectedElectivesList.map { it.toString() }.toMutableSet()
                        .isNullOrEmpty()
                ) {
                    null
                } else {
                    studentChooseState.selectedElectivesList.map { it.toString() }.toMutableSet()
                }
        }
    }
}
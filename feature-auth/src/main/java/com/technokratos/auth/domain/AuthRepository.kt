package com.technokratos.auth.domain

import com.technokratos.auth.data.network.model.Block
import com.technokratos.auth.data.network.model.Course
import com.technokratos.auth.data.network.model.Group
import com.technokratos.auth.data.network.model.Institute
import com.technokratos.auth.data.network.model.Timetable
import com.technokratos.auth.data.network.model.University

interface AuthRepository {

    suspend fun getUniversity(): List<University>

    suspend fun getInstitute(id: String): List<Institute>

    suspend fun getGroup(id: String): List<Group>

    suspend fun getCourseBlock(id: String): List<Block>

    suspend fun getCourse(id: String): List<Course>

    suspend fun getTimetable (groupId: Int, coursesIdList: List<Int>?): List<Timetable>

    fun saveUserSettings(
        universityId: Int,
        instituteId: Int,
        groupId: Int,
        blockId: Int,
        coursesIdList: List<Int>
    )
}
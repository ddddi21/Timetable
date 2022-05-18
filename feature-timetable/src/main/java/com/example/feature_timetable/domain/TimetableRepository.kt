package com.example.feature_timetable.domain

import com.example.feature_timetable.data.network.Lesson
import com.example.feature_timetable.data.network.response.CurrentWeekResponse
import com.technokratos.common.UserSPModel

interface TimetableRepository {

    suspend fun getTimetable(
        groupId: Int,
        coursesIdList: List<Int>?,
        isCurrentWeek: Boolean
    ): List<Lesson>

    suspend fun getCurrentWeek(groupId: String): Boolean

    suspend fun getUserSettings(): UserSPModel

    fun clearData()
}
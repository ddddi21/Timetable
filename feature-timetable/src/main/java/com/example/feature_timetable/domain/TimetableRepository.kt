package com.example.feature_timetable.domain

import com.example.feature_timetable.data.network.Lesson
import com.technokratos.common.UserSPModel

interface TimetableRepository {

    suspend fun getTimetable(
        groupId: Int,
        coursesIdList: List<Int>?,
        isCurrentWeek: Boolean
    ): List<Lesson>

    suspend fun getUserSettings(): UserSPModel
}
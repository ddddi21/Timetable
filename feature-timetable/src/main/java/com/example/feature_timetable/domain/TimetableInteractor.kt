package com.example.feature_timetable.domain

import com.example.feature_timetable.data.network.Timetable
import javax.inject.Inject

class TimetableInteractor @Inject constructor(
    private val timetableRepository: TimetableRepository,
) {

    suspend fun getTimetableByCurrentWeek(
        groupId: Int,
        coursesIdList: List<Int>
    ): Result<List<Timetable>> =
        runCatching {
            timetableRepository.getTimetable(groupId, coursesIdList, true)
        }

    suspend fun getTimetableByNotCurrentWeek(
        groupId: Int,
        coursesIdList: List<Int>
    ): Result<List<Timetable>> =
        runCatching {
            timetableRepository.getTimetable(groupId, coursesIdList, false)
        }
}
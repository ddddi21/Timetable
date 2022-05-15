package com.example.feature_timetable.data.repository

import com.example.feature_timetable.data.TimetableApi
import com.example.feature_timetable.data.network.Timetable
import com.example.feature_timetable.data.network.request.TimetableRequest
import com.example.feature_timetable.domain.TimetableRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimetableRepositoryImpl @Inject constructor(
    private val timetableApi: TimetableApi,
): TimetableRepository {

    override suspend fun getTimetable(
        groupId: Int,
        coursesIdList: List<Int>,
        isCurrentWeek: Boolean
    ): List<Timetable> {
        return withContext(Dispatchers.IO) {
            timetableApi.getTimetable(
                TimetableRequest(
                    groupId = groupId,
                    coursesId = coursesIdList.ifEmpty { null },
                    isCurrentWeek = isCurrentWeek
                )
            ).map { response ->
                Timetable(
                    id = response.id,
                    dayName = response.dayName,
                    startTime = response.startTime,
                    endTime = response.endTime,
                    type = response.type,
                    isEvenWeek = response.isEvenWeek,
                    classroom = response.classroom,
                    links = response.links,
                    teacherId = response.teacherId,
                    subjectId = response.subjectId,
                    groupId = response.groupId,
                    isChanged = response.isChanged
                )
            }
        }
    }
}
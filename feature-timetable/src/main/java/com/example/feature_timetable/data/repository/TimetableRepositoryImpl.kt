package com.example.feature_timetable.data.repository

import com.example.feature_timetable.data.TimetableApi
import com.example.feature_timetable.data.network.Lesson
import com.example.feature_timetable.data.network.request.TimetableRequest
import com.example.feature_timetable.data.network.response.CurrentWeekResponse
import com.example.feature_timetable.domain.TimetableRepository
import com.technokratos.common.UserSPModel
import com.technokratos.common.local.sp.UserSharedPreferences
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimetableRepositoryImpl @Inject constructor(
    private val timetableApi: TimetableApi,
    private val userSharedPreferences: UserSharedPreferences
) : TimetableRepository {

    override suspend fun getTimetable(
        groupId: Int,
        coursesIdList: List<Int>?,
        isCurrentWeek: Boolean
    ): List<Lesson> {
        return withContext(Dispatchers.IO) {
            timetableApi.getTimetable(
                TimetableRequest(
                    groupId = groupId,
                    coursesId = coursesIdList,
                    isCurrentWeek = isCurrentWeek
                )
            ).map { response ->
                Lesson(
                    id = response.id,
                    dayName = response.dayName,
                    startTime = response.startTime,
                    endTime = response.endTime,
                    type = response.type,
                    isEvenWeek = response.isEvenWeek,
                    classroom = response.classroom,
                    links = response.links,
                    teacher = response.teacher,
                    subject = response.subject,
                    groupId = response.groupId,
                    isChanged = response.isChanged
                )
            }
        }
    }

    override suspend fun getCurrentWeek(groupId: String): Boolean {
        return withContext(Dispatchers.IO) {
            timetableApi.getCurrentWeek(groupId).isEvenWeek
        }
    }

    override suspend fun getUserSettings(): UserSPModel {
        return with(userSharedPreferences) {
            UserSPModel(
                group = group,
                institute = institute,
                university = university,
                courses = courses,
                block = block
            )
        }
    }

    override fun clearData() {
        userSharedPreferences.clear()
    }
}
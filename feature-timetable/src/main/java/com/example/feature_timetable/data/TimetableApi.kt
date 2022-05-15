package com.example.feature_timetable.data

import com.example.feature_timetable.data.network.request.TimetableRequest
import com.example.feature_timetable.data.network.response.TimetableResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TimetableApi {

    @POST("student/timetable/")
    suspend fun getTimetable(@Body timetableRequest: TimetableRequest): List<TimetableResponse>

}
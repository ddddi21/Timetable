package com.example.feature_timetable.data

import com.example.feature_timetable.data.network.request.TimetableRequest
import com.example.feature_timetable.data.network.response.CurrentWeekResponse
import com.example.feature_timetable.data.network.response.TimetableResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TimetableApi {

    @POST("student/timetable/")
    suspend fun getTimetable(@Body timetableRequest: TimetableRequest): List<TimetableResponse>

    @GET("student/currentWeek/{group_id}")
    suspend fun getCurrentWeek(@Path("group_id") id: String): CurrentWeekResponse
}
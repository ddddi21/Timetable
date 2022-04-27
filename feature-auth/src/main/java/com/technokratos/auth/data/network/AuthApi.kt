package com.technokratos.auth.data.network

import com.technokratos.auth.data.network.request.TimetableRequest
import com.technokratos.auth.data.network.response.BlockResponse
import com.technokratos.auth.data.network.response.UniversityResponse
import com.technokratos.auth.data.network.response.EmailDto
import com.technokratos.auth.data.network.response.GroupResponse
import com.technokratos.auth.data.network.response.InstituteResponse
import com.technokratos.auth.data.network.response.RefreshTokenDto
import com.technokratos.auth.data.network.response.TimetableResponse
import com.technokratos.auth.data.network.response.TokensDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @GET("student/university")
    suspend fun getUniversity(): UniversityResponse

    @GET("student/instByUnivid/{id}")
    suspend fun getInstitute(@Path("id") id: String): InstituteResponse

    @GET("student/groupByInstId/{id}")
    suspend fun getGroup(@Path("id") id: String): GroupResponse

    @GET("student/blockByGroupId/{id}")
    suspend fun getCourseBlock(@Path("id") id: String): BlockResponse

    @GET("student/dop_courseByBlockId/{id}")
    suspend fun getCourse(@Path("id") id: String): GroupResponse

    @POST("student/timetable")
    suspend fun getTimetable(@Body timetableRequest: TimetableRequest): TimetableResponse
}
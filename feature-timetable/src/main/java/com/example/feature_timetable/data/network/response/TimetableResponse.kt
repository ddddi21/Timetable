package com.example.feature_timetable.data.network.response

import com.google.gson.annotations.SerializedName

data class TimetableResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("day_name")
    val dayName: Int,
    @SerializedName("start_time")
    val startTime: String,
    @SerializedName("end_time")
    val endTime: String,
    @SerializedName("type")
    val type: Int,
    @SerializedName("is_even_week")
    val isEvenWeek: Int,
    @SerializedName("classroom")
    val classroom: String?,
    @SerializedName("links")
    val links: List<String>?,
    @SerializedName("teacher")
    val teacher: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("group")
    val groupId: Int,
    @SerializedName("is_changed")
    val isChanged: Boolean
)
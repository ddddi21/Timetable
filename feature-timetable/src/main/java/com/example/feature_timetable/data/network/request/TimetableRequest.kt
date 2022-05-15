package com.example.feature_timetable.data.network.request

import com.google.gson.annotations.SerializedName

data class TimetableRequest(
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("dop_course_id")
    val coursesId: List<Int>?,
    @SerializedName("current_week")
    val isCurrentWeek: Boolean
)
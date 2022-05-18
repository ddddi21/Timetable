package com.example.feature_timetable.data.network.response

import com.google.gson.annotations.SerializedName

data class CurrentWeekResponse(
    @SerializedName("even_week")
    val isEvenWeek: Boolean,
)
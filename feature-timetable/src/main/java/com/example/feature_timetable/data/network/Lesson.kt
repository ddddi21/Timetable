package com.example.feature_timetable.data.network

data class Lesson(
    val id: Int,
    val dayName: Int,
    val startTime: String,
    val endTime: String,
    val type: Int,
    val isEvenWeek: Int,
    val classroom: String?,
    val links: List<String>?,
    val teacher: String,
    val subject: String,
    val groupId: Int,
    val isChanged: Boolean,
)
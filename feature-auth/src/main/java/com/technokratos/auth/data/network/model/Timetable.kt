package com.technokratos.auth.data.network.model

data class Timetable(
    val id: Int,
    val dayName: String,
    val startTime: String,
    val endTime: String,
    val type: String,
    val isEvenWeek: Boolean,
    val classroom: String,
    val links: List<String>,
    val teacherId: Int,
    val subjectId: Int,
    val groupId: Int
)
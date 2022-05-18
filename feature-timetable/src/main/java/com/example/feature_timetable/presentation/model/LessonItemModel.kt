package com.example.feature_timetable.presentation.model

import com.example.feature_timetable.R
import com.technokratos.common.base.adapter.ViewType

data class LessonItemModel (
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
): ViewType(R.layout.item_lesson)

fun Int.getSubjectTypeAppearance(): Int {
    return when (this) {
        1 -> R.string.online_practice
        2 -> R.string.offline_practice
        3 -> R.string.online_lecture
        4 -> R.string.offline_lecture
        5 -> R.string.canceled

        else -> {0}
    }
}

fun Int.getDayNameAppearance(): Int {
    return when (this) {
        1 -> R.string.monday
        2 -> R.string.tuesday
        3 -> R.string.wednesday
        4 -> R.string.thursday
        5 -> R.string.friday
        6 -> R.string.saturday

        else-> {0}
    }
}
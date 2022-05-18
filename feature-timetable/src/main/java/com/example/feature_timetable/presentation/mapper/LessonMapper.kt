package com.example.feature_timetable.presentation.mapper

import com.example.feature_timetable.data.network.Lesson
import com.example.feature_timetable.presentation.model.LessonItemModel

fun mapLessonsToLessonItemModel(
    lesson: Lesson
): LessonItemModel {
    return LessonItemModel(
        id = lesson.id,
        dayName = lesson.dayName,
        endTime = lesson.endTime,
        type = lesson.type,
        isEvenWeek = lesson.isEvenWeek,
        classroom = lesson.classroom,
        teacher = lesson.teacher,
        subject = lesson.subject,
        isChanged = lesson.isChanged,
        startTime = lesson.startTime,
        groupId = lesson.groupId,
        links = lesson.links
    )
}
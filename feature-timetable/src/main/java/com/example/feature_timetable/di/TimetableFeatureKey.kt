package com.example.feature_timetable.di

import com.example.feature_timetable.domain.TimetableRepository

interface TimetableFeatureKey {

    fun provideTimetableRepository(): TimetableRepository
}
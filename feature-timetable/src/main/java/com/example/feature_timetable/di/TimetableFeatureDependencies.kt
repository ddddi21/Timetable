package com.example.feature_timetable.di

import com.technokratos.common.local.sp.UserSharedPreferences

interface TimetableFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences
}
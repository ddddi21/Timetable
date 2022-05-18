package com.technokratos.splash.di

import com.example.feature_timetable.domain.TimetableRepository
import com.technokratos.common.local.sp.UserSharedPreferences

interface SplashFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences

    fun provideTimetableRepository(): TimetableRepository
}
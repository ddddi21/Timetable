package com.technokratos.splash.domain

import com.example.feature_timetable.domain.TimetableRepository
import com.technokratos.common.local.sp.UserSharedPreferences
import javax.inject.Inject

class SplashInteractor @Inject constructor(
    private val userSharedPreferences: UserSharedPreferences,
    private val timetableRepository: TimetableRepository
) {
    suspend fun isUserLoggedIn(): Boolean {
       return !timetableRepository.getUserSettings().group.isNullOrEmpty()
    }
}
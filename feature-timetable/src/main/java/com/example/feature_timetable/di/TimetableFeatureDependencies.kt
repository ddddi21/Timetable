package com.example.feature_timetable.di

import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.UnauthorizedRetrofit
import com.technokratos.common.local.sp.UserSharedPreferences

interface TimetableFeatureDependencies {

    @UnauthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator

    fun provideUserSharedPreferences(): UserSharedPreferences
}
package com.technokratos.splash.di

import com.technokratos.common.local.sp.UserSharedPreferences

interface SplashFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences
}
package com.technokratos.auth.di

import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.UnauthorizedRetrofit
import com.technokratos.common.local.sp.UserSharedPreferences
import com.technokratos.common.resources.ResourceManager

interface AuthFeatureDependencies {

    fun provideUserSharedPreferences(): UserSharedPreferences

    @UnauthorizedRetrofit
    fun provideUnauthorizedNetworkApiCreator(): NetworkApiCreator

    fun provideResourceManager(): ResourceManager
}
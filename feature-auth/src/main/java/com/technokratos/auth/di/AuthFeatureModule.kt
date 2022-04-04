package com.technokratos.auth.di

import com.technokratos.auth.data.network.AuthApi
import com.technokratos.auth.data.repository.AuthRepositoryImpl
import com.technokratos.auth.domain.AuthRepository
import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.UnauthorizedRetrofit
import com.technokratos.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class AuthFeatureModule {

    @Provides
    @FeatureScope
    fun provideAuthApi(@UnauthorizedRetrofit apiCreator: NetworkApiCreator): AuthApi {
        return apiCreator.create(AuthApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository = authRepository
}
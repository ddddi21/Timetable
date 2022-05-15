package com.example.feature_timetable.di

import com.example.feature_timetable.data.TimetableApi
import com.example.feature_timetable.data.repository.TimetableRepositoryImpl
import com.example.feature_timetable.domain.TimetableRepository
import com.technokratos.common.data.network.NetworkApiCreator
import com.technokratos.common.di.UnauthorizedRetrofit
import com.technokratos.common.di.scope.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class TimetableFeatureModule {

    @Provides
    @FeatureScope
    fun provideTimetableApi(@UnauthorizedRetrofit apiCreator: NetworkApiCreator): TimetableApi {
        return apiCreator.create(TimetableApi::class.java)
    }

    @Provides
    @FeatureScope
    fun provideTimetableRepository(timetableRepository: TimetableRepositoryImpl): TimetableRepository =
        timetableRepository
}
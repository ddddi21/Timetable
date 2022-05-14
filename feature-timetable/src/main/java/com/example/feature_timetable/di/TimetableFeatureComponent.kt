package com.example.feature_timetable.di

import com.example.feature_timetable.TimetableRouter
import com.technokratos.common.di.CommonApi
import com.technokratos.common.di.scope.FeatureScope
import com.example.feature_timetable.presentation.di.TimetableComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        TimetableFeatureDependencies::class
    ],
    modules = [
        TimetableFeatureModule::class
    ]
)
@FeatureScope
interface TimetableFeatureComponent : TimetableFeatureKey {

    fun timetableComponentFactory(): TimetableComponent.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun router(timetableRouter: TimetableRouter): Builder

        fun withDependencies(deps: TimetableFeatureDependencies): Builder

        fun build(): TimetableFeatureComponent
    }

    @Component(
        dependencies = [
            CommonApi::class
        ]
    )
    interface TimetableFeatureDependenciesComponent : TimetableFeatureDependencies
}
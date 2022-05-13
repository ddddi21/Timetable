package com.example.feature_timetable.di

import com.example.feature_timetable.TimetableRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class TimetableFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val timetableRouter: TimetableRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val timetableFeatureDependencies = DaggerTimetableFeatureComponent_TimetableFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerTimetableFeatureComponent.builder()
            .withDependencies(timetableFeatureDependencies)
            .router(timetableRouter)
            .build()
    }
}
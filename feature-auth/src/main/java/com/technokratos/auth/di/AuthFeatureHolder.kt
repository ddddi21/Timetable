package com.technokratos.auth.di

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class AuthFeatureHolder @Inject constructor(
    featureContainer: FeatureContainer,
    private val authRouter: AuthRouter
) : FeatureApiHolder(featureContainer) {

    override fun initializeDependencies(): Any {
        val authFeatureDependencies = DaggerAuthFeatureComponent_AuthFeatureDependenciesComponent.builder()
            .commonApi(commonApi())
            .build()
        return DaggerAuthFeatureComponent.builder()
            .withDependencies(authFeatureDependencies)
            .router(authRouter)
            .build()
    }
}
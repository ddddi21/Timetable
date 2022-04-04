package com.technokratos.app.router

import com.technokratos.auth.router.AuthRouter
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.common.router.NavigateBackRouter
import com.technokratos.splash.SplashRouter
import dagger.Binds
import dagger.Module

@Module
abstract class RouterModule {

    @ApplicationScope
    @Binds
    abstract fun provideSplashRouter(routerImpl: RouterImpl): SplashRouter

    @ApplicationScope
    @Binds
    abstract fun provideAuthRouter(routerImpl: RouterImpl): AuthRouter

    @ApplicationScope
    @Binds
    abstract fun provideNavigateBackRouter(routerImpl: RouterImpl): NavigateBackRouter

    @ApplicationScope
    @Binds
    abstract fun provideMainRouter(routerImpl: RouterImpl): MainRouter
}
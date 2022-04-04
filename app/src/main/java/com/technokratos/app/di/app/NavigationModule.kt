package com.technokratos.app.di.app

import com.technokratos.app.NavControllerProvider
import com.technokratos.common.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {

    @JvmStatic
    @ApplicationScope
    @Provides
    fun provideNavControllerProvider(): NavControllerProvider = NavControllerProvider()
}
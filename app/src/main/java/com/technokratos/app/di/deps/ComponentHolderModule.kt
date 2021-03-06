package com.technokratos.app.di.deps

import com.technokratos.app.App
import com.technokratos.auth.di.AuthFeatureHolder
import com.technokratos.auth.di.AuthFeatureKey
import com.technokratos.common.di.FeatureApiHolder
import com.technokratos.common.di.FeatureContainer
import com.technokratos.common.di.scope.ApplicationScope
import com.technokratos.core_db.di.DbApi
import com.technokratos.core_db.di.DbHolder
import com.technokratos.splash.di.SplashFeatureKey
import com.technokratos.splash.di.SplashFeatureHolder
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ComponentHolderModule {

    @ApplicationScope
    @Binds
    fun provideFeatureContainer(application: App): FeatureContainer

    @ApplicationScope
    @Binds
    @ClassKey(SplashFeatureKey::class)
    @IntoMap
    fun provideSplashFeatureHolder(splashFeatureHolder: SplashFeatureHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(DbApi::class)
    @IntoMap
    fun provideDbFeature(dbHolder: DbHolder): FeatureApiHolder

    @ApplicationScope
    @Binds
    @ClassKey(AuthFeatureKey::class)
    @IntoMap
    fun provideAuthFeatureHolder(authFeatureHolder: AuthFeatureHolder): FeatureApiHolder
}
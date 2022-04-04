package com.technokratos.splash.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import com.technokratos.splash.SplashRouter
import com.technokratos.splash.domain.SplashInteractor
import com.technokratos.splash.presentation.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class SplashModule {

    @Provides
    internal fun provideSplashViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): SplashViewModel {
        return ViewModelProvider(fragment, factory).get(SplashViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun provideSplashViewModelFromStore(router: SplashRouter, splashInteractor: SplashInteractor): ViewModel {
        return SplashViewModel(router, splashInteractor)
    }
}
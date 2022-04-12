package com.technokratos.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.router.AuthRouter
import com.technokratos.auth.presentation.auth.AuthViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import com.technokratos.common.resources.ResourceManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ViewModelModule::class
    ]
)
class AuthModule {

    @Provides
    internal fun provideAuthViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): AuthViewModel {
        return ViewModelProvider(fragment, factory).get(AuthViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun provideAuthViewModelFromStore(
        router: AuthRouter,
        interactor: AuthInteractor,
        resourceManager: ResourceManager,

    ): ViewModel {
        return AuthViewModel(router, interactor, resourceManager)
    }
}
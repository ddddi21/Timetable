package com.technokratos.auth.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.technokratos.auth.domain.AuthInteractor
import com.technokratos.auth.presentation.registration.RegistrationViewModel
import com.technokratos.auth.router.AuthRouter
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
class RegistrationModule {

    @Provides
    internal fun provideRegistrationViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): RegistrationViewModel {
        return ViewModelProvider(fragment, factory).get(RegistrationViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun provideRegistrationViewModelFromStore(authRouter: AuthRouter, interactor: AuthInteractor, resourceManager: ResourceManager): ViewModel {
        return RegistrationViewModel(authRouter, interactor, resourceManager)
    }
}
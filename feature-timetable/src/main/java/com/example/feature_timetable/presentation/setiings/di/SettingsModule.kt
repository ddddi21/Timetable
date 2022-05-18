package com.example.feature_timetable.presentation.setiings.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_timetable.TimetableRouter
import com.example.feature_timetable.domain.TimetableInteractor
import com.example.feature_timetable.presentation.setiings.SettingsViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class SettingsModule {

    @Provides
    internal fun provideSettingsViewModelFromStore(
        fragment: Fragment,
        factory: ViewModelProvider.Factory
    ): SettingsViewModel {
        return ViewModelProvider(fragment, factory).get(SettingsViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun provideSettingsViewModelFromStore(
        interactor: TimetableInteractor,
        router: TimetableRouter
    ): ViewModel {
        return SettingsViewModel(interactor, router)
    }
}
package com.example.feature_timetable.presentation.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_timetable.TimetableRouter
import com.example.feature_timetable.presentation.TimetableViewModel
import com.technokratos.common.di.viewmodel.ViewModelKey
import com.technokratos.common.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule::class])
class TimetableModule {

    @Provides
    internal fun provideTimetableViewModelFromStore(fragment: Fragment, factory: ViewModelProvider.Factory): TimetableViewModel {
        return ViewModelProvider(fragment, factory).get(TimetableViewModel::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(TimetableViewModel::class)
    fun provideTimetableViewModelFromStore(router: TimetableRouter): ViewModel {
        return TimetableViewModel(router)
    }
}
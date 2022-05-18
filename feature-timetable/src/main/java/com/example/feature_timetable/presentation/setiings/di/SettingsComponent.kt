package com.example.feature_timetable.presentation.setiings.di

import androidx.fragment.app.Fragment
import com.example.feature_timetable.presentation.setiings.SettingsFragment
import com.technokratos.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SettingsModule::class
    ]
)
@ScreenScope
interface SettingsComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SettingsComponent
    }

    fun inject(fragment: SettingsFragment)
}
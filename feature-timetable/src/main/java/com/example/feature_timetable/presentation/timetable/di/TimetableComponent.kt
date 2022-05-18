package com.example.feature_timetable.presentation.timetable.di

import androidx.fragment.app.Fragment
import com.technokratos.common.di.scope.ScreenScope
import com.example.feature_timetable.presentation.timetable.TimetableFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        TimetableModule::class
    ]
)
@ScreenScope
interface TimetableComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): TimetableComponent
    }

    fun inject(fragment: TimetableFragment)
}
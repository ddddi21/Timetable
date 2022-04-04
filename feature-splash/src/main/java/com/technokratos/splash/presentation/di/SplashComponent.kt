package com.technokratos.splash.presentation.di

import androidx.fragment.app.Fragment
import com.technokratos.common.di.scope.ScreenScope
import com.technokratos.splash.presentation.SplashFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        SplashModule::class
    ]
)
@ScreenScope
interface SplashComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): SplashComponent
    }

    fun inject(fragment: SplashFragment)
}
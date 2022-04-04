package com.technokratos.auth.presentation.di

import androidx.fragment.app.Fragment
import com.technokratos.auth.presentation.registration.RegistrationFragment
import com.technokratos.common.di.scope.ScreenScope
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        RegistrationModule::class
    ]
)
@ScreenScope
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance fragment: Fragment
        ): RegistrationComponent
    }

    fun inject(fragment: RegistrationFragment)
}
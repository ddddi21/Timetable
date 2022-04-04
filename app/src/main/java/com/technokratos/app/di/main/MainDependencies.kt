package com.technokratos.app.di.main

import com.technokratos.app.NavControllerProvider
import com.technokratos.app.router.MainRouter
import com.technokratos.app.di.deps.ComponentDependencies

interface MainDependencies : ComponentDependencies {

    fun navControllerProviderDeps(): NavControllerProvider

    fun mainRouter(): MainRouter
}
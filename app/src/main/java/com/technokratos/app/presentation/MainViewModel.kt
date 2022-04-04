package com.technokratos.app.presentation

import com.technokratos.app.router.MainRouter
import com.technokratos.common.base.BaseViewModel

class MainViewModel(
    private val mainRouter: MainRouter
) : BaseViewModel() {

    fun onViewCreated() {
        mainRouter.navigateToAuthNavGraph()
    }
}
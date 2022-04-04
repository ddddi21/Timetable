package com.technokratos.app

import androidx.navigation.NavController
import javax.inject.Provider

class NavControllerProvider : Provider<NavController?> {

    private var navController: NavController? = null

    override fun get(): NavController? = navController

    fun attachNavController(navController: NavController, graph: Int) {
        navController.setGraph(graph)
        this.navController = navController
    }

    fun detachNavController(navController: NavController) {
        if (this.navController == navController) {
            this.navController = null
        }
    }
}
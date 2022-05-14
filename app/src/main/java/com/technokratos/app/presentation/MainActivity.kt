package com.technokratos.app.presentation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.technokratos.app.NavControllerProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.technokratos.app.R
import com.technokratos.app.di.main.MainComponent
import com.technokratos.common.base.BaseActivity
import com.technokratos.app.di.deps.findComponentDependencies
import kotlinx.android.synthetic.main.activity_main.bottomNavigationView
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var navigator: NavControllerProvider

    private var navController = NavController(this)

    override fun layoutResource() = R.layout.activity_main

    override fun inject() {
        MainComponent
            .init(this, findComponentDependencies())
            .inject(this)
    }

    override fun initViews() {
        setUpNavigation()
        viewModel.onViewCreated()
    }

    override fun subscribe(viewModel: MainViewModel) {
        // TODO
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachNavController(navController)
    }

    private fun setUpBottomNavVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment,
                R.id.authFragment -> bottomNavigationView.visibility = View.GONE

                else -> bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment?
                ?: return
        navController = navHostFragment.navController
        navigator.attachNavController(navController, R.navigation.main_nav_graph)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavView, navController)
        setUpBottomNavVisibility()
    }
}
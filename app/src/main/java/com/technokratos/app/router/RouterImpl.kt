package com.technokratos.app.router

import android.os.Bundle
import com.example.feature_timetable.presentation.timetable.TimetableFragment
import com.technokratos.app.R
import com.technokratos.app.NavControllerProvider
import com.technokratos.auth.presentation.state.StudentChooseState
import javax.inject.Inject

private const val STUDENT_CHOOSE_STATE_KEY = "choose_state_key"

class RouterImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : Router {

    override fun toLogin() {
        navigateTo(R.id.action_splashFragment_to_authFragment)
    }

    override fun toMain() {
        navigateTo(R.id.timetableFragment)
    }

    override fun navigateToMain(chooseState: StudentChooseState) {
        navigateTo(R.id.timetableFragment, TimetableFragment.buildArgs(chooseState))
    }

    override fun navigateToAuthNavGraph() {
        navigateTo(R.id.auth_nav_graph)
    }

    override fun navigateToAuth() {
        navigateTo(R.id.auth_nav_graph)
    }

    override fun goToPreviousScreen() {
        navControllerProvider.get()?.popBackStack()
    }

    private fun navigateTo(actionId: Int, bundle: Bundle? = null) {
        navControllerProvider.get()
            ?.navigate(actionId, bundle)
    }
}
package com.example.feature_timetable.presentation.setiings

import com.example.feature_timetable.TimetableRouter
import com.example.feature_timetable.domain.TimetableInteractor
import com.technokratos.common.base.BaseViewModel

class SettingsViewModel(
    private val interactor: TimetableInteractor,
    private val router: TimetableRouter
) : BaseViewModel() {

    fun onClearData() {
        interactor.clearData()
        router.navigateToAuth()
    }
}
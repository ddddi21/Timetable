package com.example.feature_timetable.presentation

import com.example.feature_timetable.TimetableRouter
import com.example.feature_timetable.domain.TimetableInteractor
import com.technokratos.auth.presentation.state.StudentChooseState
import com.technokratos.common.base.BaseViewModel

class TimetableViewModel(
    private val router: TimetableRouter,
    private val interactor: TimetableInteractor
) : BaseViewModel() {

    fun loadTimetable(studentChooseState: StudentChooseState) {
        doCoroutineWork {
            val result = interactor.getTimetableByCurrentWeek(
                studentChooseState.selectedGroupId,
                studentChooseState.selectedElectivesList
            )

            if(result.isSuccess) {
                
            }
        }
    }
}
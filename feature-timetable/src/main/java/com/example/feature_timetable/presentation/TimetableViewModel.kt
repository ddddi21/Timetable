package com.example.feature_timetable.presentation

import com.example.feature_timetable.TimetableRouter
import com.example.feature_timetable.domain.TimetableInteractor
import com.example.feature_timetable.presentation.mapper.mapLessonsToLessonItemModel
import com.example.feature_timetable.presentation.model.LessonItemModel
import com.technokratos.common.UserSPModel
import com.technokratos.common.base.BaseViewModel
import com.technokratos.common.base.adapter.ViewType
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class TimetableViewModel(
    private val router: TimetableRouter,
    private val interactor: TimetableInteractor
) : BaseViewModel() {

    private val _mondayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val mondayListFlow = _mondayListFlow.asSharedFlow()

    private val _tuesdayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val tuesdayListFlow = _tuesdayListFlow.asSharedFlow()

    private val _wednesdayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val wednesdayListFlow = _wednesdayListFlow.asSharedFlow()

    private val _thursdayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val thursdayListFlow = _thursdayListFlow.asSharedFlow()

    private val _fridayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val fridayListFlow = _fridayListFlow.asSharedFlow()

    private val _saturdayListFlow =
        MutableSharedFlow<List<ViewType>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val saturdayListFlow = _saturdayListFlow.asSharedFlow()

    private val _userSettings =
        MutableSharedFlow<UserSPModel>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _currentWeek = MutableSharedFlow<Boolean>()
    val currentWeek = _currentWeek.asSharedFlow()


    init {
        checkUserSetting()
    }

    fun loadTimetableByCurrentWeek() {
        doCoroutineWork {
            val userSettings = _userSettings.replayCache.first()

            val result = interactor.getTimetableByCurrentWeek(
                userSettings.group?.toInt()!!,
                userSettings.courses?.toList()?.map { it.toInt() }
            ).map { lessonsList -> lessonsList.map { mapLessonsToLessonItemModel(it) } }

            if (result.isSuccess) {
                result.getOrNull()?.let { lessonsList ->
                    filterLessonsListByDay(lessonsList)
                }
            }
        }
    }

    fun loadTimetableByNotCurrentWeek() {
        doCoroutineWork {
            val userSettings = _userSettings.replayCache.first()

            val result = interactor.getTimetableByNotCurrentWeek(
                userSettings.group?.toInt()!!,
                userSettings.courses?.toList()?.map { it.toInt() }
            ).map { lessonsList -> lessonsList.map { mapLessonsToLessonItemModel(it) } }

            if (result.isSuccess) {
                result.getOrNull()?.let { lessonsList ->
                    filterLessonsListByDay(lessonsList)
                }
            }
        }
    }

    private fun checkUserSetting() {
        doCoroutineWork {
            val result = interactor.getUserSettings()

            if (result.isSuccess) {
                result.getOrNull()?.let { settings ->
                    setCurrentWeek(settings.group!!)
                    _userSettings.emit(settings)
                    loadTimetableByCurrentWeek()
                }
            }
        }
    }

    private fun filterLessonsListByDay(list: List<LessonItemModel>) {
        doCoroutineWork {
            val mondayList = mutableListOf<ViewType>()
            val tuesdayList = mutableListOf<ViewType>()
            val wednesdayList = mutableListOf<ViewType>()
            val thursdayList = mutableListOf<ViewType>()
            val fridayList = mutableListOf<ViewType>()
            val saturdayList = mutableListOf<ViewType>()

            list.map { lesson ->
                when (lesson.dayName) {
                    1 -> mondayList.add(lesson)
                    2 -> tuesdayList.add(lesson)
                    3 -> wednesdayList.add(lesson)
                    4 -> thursdayList.add(lesson)
                    5 -> fridayList.add(lesson)
                    6 -> saturdayList.add(lesson)
                    else -> {
                    }
                }
            }
            _mondayListFlow.emit(mondayList)
            _tuesdayListFlow.emit(tuesdayList)
            _wednesdayListFlow.emit(wednesdayList)
            _thursdayListFlow.emit(thursdayList)
            _fridayListFlow.emit(fridayList)
            _saturdayListFlow.emit(saturdayList)
        }
    }

    private fun setCurrentWeek(groupId: String) {
        doCoroutineWork {
            val result = interactor.getCurrentWeek(groupId)

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _currentWeek.emit(it)
                }
            }
        }
    }
}
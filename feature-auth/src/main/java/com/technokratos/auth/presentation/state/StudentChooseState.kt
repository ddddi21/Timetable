package com.technokratos.auth.presentation.state

import com.technokratos.auth.presentation.model.ScreenType

data class StudentChooseState(
    var isNeedToShowBackArrow: Boolean = false,
    var isItemChosen: Boolean = false,
    var screenType: ScreenType = ScreenType.UNIVERSITY,
    var selectedUniversityId: Int = 0,
    var selectedInstituteId: Int = 0,
    var selectedGroupId: Int = 0,
    var selectedElectivesId: List<Int> = emptyList()
)
package com.technokratos.auth.presentation.state

import com.technokratos.auth.presentation.model.ScreenType
import java.io.Serializable

data class StudentChooseState(
    var isNeedToShowBackArrow: Boolean = false,
    var isItemChosen: Boolean = false,
    var screenType: ScreenType = ScreenType.UNIVERSITY,
    var selectedUniversityId: Int = 0,
    var selectedInstituteId: Int = 0,
    var selectedGroupId: Int = 0,
    var selectedElectiveId: Int = 0,
    var selectedElectivesList: MutableList<Int> = mutableListOf()
): Serializable
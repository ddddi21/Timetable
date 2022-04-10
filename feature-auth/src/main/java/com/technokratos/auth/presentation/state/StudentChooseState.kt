package com.technokratos.auth.presentation.state

import com.technokratos.auth.presentation.model.ScreenType

data class StudentChooseState(
    val isNeedToShowBackArrow: Boolean = false,
    val isItemChosen: Boolean = false,
    val screenType: ScreenType = ScreenType.UNIVERSITY
)
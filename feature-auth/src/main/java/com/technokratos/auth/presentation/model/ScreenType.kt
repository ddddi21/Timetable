package com.technokratos.auth.presentation.model

import androidx.annotation.StringRes
import com.example.feature_auth.R

enum class ScreenType {
    UNIVERSITY, INSTITUTE, GROUP, ELECTIVES
}

enum class ScreenTypeAppearance(
    @StringRes val remarkStatusTitle: Int,
) {
    UNIVERSITY(
        R.string.student_set_up_choose_university
    ),
    INSTITUTE(
        R.string.student_set_up_choose_institute
    ),
    GROUP(
        R.string.student_set_up_choose_group
    ),
    ELECTIVES(
        R.string.student_set_up_choose_elective_courses
    )
}

fun ScreenType.getScreenTypeAppearance(): ScreenTypeAppearance {
    return when (this) {
        ScreenType.UNIVERSITY -> ScreenTypeAppearance.UNIVERSITY
        ScreenType.INSTITUTE -> ScreenTypeAppearance.INSTITUTE
        ScreenType.GROUP -> ScreenTypeAppearance.GROUP
        ScreenType.ELECTIVES -> ScreenTypeAppearance.ELECTIVES
    }
}
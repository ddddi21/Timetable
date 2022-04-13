package com.technokratos.auth.presentation.mapper

import com.technokratos.auth.domain.model.University
import com.technokratos.auth.presentation.itemView.StudentItemView
import com.technokratos.auth.presentation.model.StudentItemModel


fun mapUniversityToUniversityItemModel(
    university: University,
    onItemClicked: ((StudentItemModel) -> Unit)? = null
): StudentItemModel {
    return StudentItemModel(
        id = university.id,
        title = university.title,
        onItemClicked = onItemClicked
    )
}
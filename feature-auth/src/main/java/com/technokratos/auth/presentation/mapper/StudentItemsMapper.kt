package com.technokratos.auth.presentation.mapper

import com.technokratos.auth.domain.model.University
import com.technokratos.auth.presentation.model.StudentItemModel


fun mapUniversityToUniversityItemModel(
    university: University,
    onItemClicked: (() -> Unit)? = null
): StudentItemModel {
    return StudentItemModel(
        id = university.id,
        title = university.title,
        onItemClicked = onItemClicked
    )
}

fun mapTitleToUniversityItemModel( // TODO (delete later)
    title: String,
    onItemClicked: (() -> Unit)? = null
): StudentItemModel {
    return StudentItemModel(
        title = title,
        onItemClicked = onItemClicked
    )
}
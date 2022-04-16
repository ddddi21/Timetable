package com.technokratos.auth.presentation.mapper

import com.technokratos.auth.domain.model.StudentItem
import com.technokratos.auth.presentation.model.ElectiveItemModel
import com.technokratos.auth.presentation.model.StudentItemModel


fun mapStudentItemToStudentItemModel(
    studentItem: StudentItem,
    onItemClicked: ((StudentItemModel) -> Unit)? = null
): StudentItemModel {
    return StudentItemModel(
        id = studentItem.id,
        title = studentItem.title,
        onItemClicked = onItemClicked
    )
}

fun mapStudentItemToElectiveItemModel(
    studentItem: StudentItem,
    onItemClicked: ((ElectiveItemModel) -> Unit)? = null
): ElectiveItemModel {
    return ElectiveItemModel(
        id = studentItem.id,
        title = studentItem.title,
        onItemClicked = onItemClicked
    )
}
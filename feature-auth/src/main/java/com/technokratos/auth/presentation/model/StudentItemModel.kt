package com.technokratos.auth.presentation.model

import com.example.feature_auth.R
import com.technokratos.auth.presentation.itemView.StudentItemView
import com.technokratos.common.base.adapter.ViewType

data class StudentItemModel (
    val id: Int = 0, // TODO
    val title: String,
    val onItemClicked: ((StudentItemModel) -> Unit)? = null,
    var isChecked: Boolean = false,
) : ViewType(R.layout.item_student_choose)

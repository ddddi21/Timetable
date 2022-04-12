package com.technokratos.auth.presentation.model

import com.example.feature_auth.R
import com.technokratos.common.base.adapter.ViewType

data class StudentItemModel (
    val id: Int = 0, // TODO
    val title: String,
    val onItemClicked: (() -> Unit)? = null
) : ViewType(R.layout.item_student_choose)

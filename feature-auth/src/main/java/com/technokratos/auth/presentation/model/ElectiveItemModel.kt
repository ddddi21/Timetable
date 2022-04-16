package com.technokratos.auth.presentation.model

import com.example.feature_auth.R
import com.technokratos.common.base.adapter.ViewType

data class ElectiveItemModel(
    val id: Int,
    val title: String,
    val onItemClicked: ((ElectiveItemModel) -> Unit)? = null,
): ViewType(R.layout.item_elective)
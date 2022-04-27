package com.technokratos.auth.data.network.model

data class Institute(
    val id: Int,
    val name: String,
    val shortName: String,
    val link: String,
    val universityId: Int
)
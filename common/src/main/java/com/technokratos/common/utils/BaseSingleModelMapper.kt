package com.technokratos.common.utils

interface BaseSingleModelMapper<M, E> {

    fun map(model: M): E
}
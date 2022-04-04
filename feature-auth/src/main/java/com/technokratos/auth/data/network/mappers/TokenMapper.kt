package com.technokratos.auth.data.network.mappers

import com.technokratos.auth.data.network.model.Tokens
import com.technokratos.auth.data.network.response.TokensDto
import com.technokratos.common.utils.BaseSingleModelMapper
import javax.inject.Inject

class TokenMapper @Inject constructor() : BaseSingleModelMapper<TokensDto, Tokens> {

    override fun map(model: TokensDto): Tokens {
        return Tokens(
            authToken = model.accessToken,
            refreshToken = model.refreshToken
        )
    }
}
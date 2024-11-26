package com.bakery.auth.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenDto(
    @SerialName("refreshToken")
    val refreshToken: String
)
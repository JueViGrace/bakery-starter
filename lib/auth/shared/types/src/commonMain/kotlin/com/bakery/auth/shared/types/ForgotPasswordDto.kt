package com.bakery.auth.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForgotPasswordDto(
    @SerialName("username")
    val username: String,
)

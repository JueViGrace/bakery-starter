package com.bakery.user.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserByIdDto(
    @SerialName("id")
    val id: String
)

package com.bakery.order.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrdersByUserDto(
    @SerialName("user_id")
    val userId: String,
)

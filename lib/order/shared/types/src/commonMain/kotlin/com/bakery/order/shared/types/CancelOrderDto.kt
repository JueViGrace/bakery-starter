package com.bakery.order.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CancelOrderDto(
    @SerialName("id")
    val id: String,
)

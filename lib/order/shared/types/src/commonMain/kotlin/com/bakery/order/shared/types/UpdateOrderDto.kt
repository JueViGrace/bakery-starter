package com.bakery.order.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateOrderDto(
    @SerialName("id")
    val id: String,
    @SerialName("status")
    val status: String
)

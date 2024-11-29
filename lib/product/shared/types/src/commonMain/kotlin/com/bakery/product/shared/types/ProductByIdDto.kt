package com.bakery.product.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductByIdDto(
    @SerialName("id")
    val id: String
)

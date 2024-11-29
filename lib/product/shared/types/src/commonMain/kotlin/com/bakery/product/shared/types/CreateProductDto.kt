package com.bakery.product.shared.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProductDto(
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("price")
    val price: Double,
    @SerialName("stock")
    val stock: Int,
    @SerialName("hasStock")
    val hasStock: Boolean,
    @SerialName("discount")
    val discount: Double,
    @SerialName("images")
    val images: List<String>
)

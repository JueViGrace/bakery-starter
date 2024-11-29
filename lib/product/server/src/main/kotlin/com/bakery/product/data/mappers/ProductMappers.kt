package com.bakery.product.data.mappers

import com.bakery.core.database.Bakery_product
import com.bakery.core.shared.types.Constants
import com.bakery.product.shared.types.CreateProductDto
import com.bakery.product.shared.types.ProductDto
import kotlin.text.split
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

fun Bakery_product.toDto(): ProductDto = ProductDto(
    id = id,
    name = name,
    description = description,
    category = category,
    price = price,
    stock = stock.toInt(),
    hasStock = has_stock.toInt() == 1,
    discount = discount,
    rating = rating,
    images = images.split(","),
)

@OptIn(ExperimentalUuidApi::class)
fun CreateProductDto.toDb(images: List<String>): Bakery_product = Bakery_product(
    id = Uuid.random().toString(),
    name = name,
    description = description,
    category = category,
    price = price,
    stock = stock.toLong(),
    has_stock = if (hasStock) 1 else 0,
    discount = discount,
    rating = 0.0,
    images = images.joinToString(","),
    created_at = Constants.currentTime,
    updated_at = Constants.currentTime,
    deleted_at = null
)

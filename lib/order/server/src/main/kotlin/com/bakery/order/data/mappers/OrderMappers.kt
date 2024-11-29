package com.bakery.order.data.mappers

import com.bakery.core.database.Bakery_order
import com.bakery.order.shared.types.OrderDto

typealias BakeryOrder = Bakery_order

fun BakeryOrder.toDto(): OrderDto = OrderDto(
    id = id,
    totalAmount = total_amount,
    paymentMethod = payment_method,
    status = status,
    userId = user_id,
    createdAt = created_at,
    details = emptyList(),
)

package com.bakery.order.data.mappers

import com.bakery.core.database.Bakery_order
import com.bakery.core.database.FindAllWithLines
import com.bakery.core.database.FindOneWithLines
import com.bakery.order.shared.types.OrderDetails
import com.bakery.order.shared.types.OrderDto

typealias BakeryOrder = Bakery_order
typealias BakeryOrdersWithLines = FindAllWithLines
typealias BakeryOrderWithLines = FindOneWithLines

fun BakeryOrder.toDto(): OrderDto = OrderDto(
    id = id,
    totalAmount = total_amount,
    paymentMethod = payment_method,
    status = status,
    userId = user_id,
    createdAt = created_at,
    details = emptyList(),
)

// todo: this should be done in other way

fun List<BakeryOrdersWithLines>.toDto(): List<OrderDto> {
    val group: Map<OrderDto, List<BakeryOrdersWithLines>> = this.groupBy { row ->
        OrderDto(
            id = row.id,
            totalAmount = row.total_amount,
            paymentMethod = row.payment_method,
            status = row.status,
            userId = row.user_id,
            createdAt = row.created_at,
            details = emptyList(),
        )
    }

    val dto: List<OrderDto> = group.map { (order: OrderDto, rows: List<BakeryOrdersWithLines>) ->
        return@map OrderDto(
            id = order.id,
            totalAmount = order.totalAmount,
            paymentMethod = order.paymentMethod,
            status = order.status,
            userId = order.userId,
            createdAt = order.createdAt,
            details = rows.toDetails()
        )
    }

    return dto
}

fun List<BakeryOrdersWithLines>.toDetails(): List<OrderDetails> {
    this.firstOrNull { row -> row.order_id == null } ?: return emptyList()

    val details: List<OrderDetails> = this.map { row ->
        return@map OrderDetails(
            orderId = row.order_id ?: "",
            productId = row.product_id ?: "",
            totalPrice = row.total_price ?: 0.0,
            quantity = row.quantity?.toInt() ?: 0,
            name = row.product_name ?: "",
            category = row.category ?: "",
            price = row.price ?: 0.0,
            discount = row.discount ?: 0.0,
            rating = row.rating ?: 0.0,
            images = row.images?.split(",") ?: emptyList(),
        )
    }

    return details
}

fun List<BakeryOrderWithLines>.toDto(): OrderDto? {
    val group: Map<OrderDto, List<BakeryOrderWithLines>> = this.groupBy { row ->
        OrderDto(
            id = row.id,
            totalAmount = row.total_amount,
            paymentMethod = row.payment_method,
            status = row.status,
            userId = row.user_id,
            createdAt = row.created_at,
            details = emptyList(),
        )
    }

    val dto: OrderDto? = group.map { (order: OrderDto, rows: List<BakeryOrderWithLines>) ->
        return@map OrderDto(
            id = order.id,
            totalAmount = order.totalAmount,
            paymentMethod = order.paymentMethod,
            status = order.status,
            userId = order.userId,
            createdAt = order.createdAt,
            details = rows.toDetails()
        )
    }.firstOrNull()

    return dto
}

fun List<BakeryOrderWithLines>.toDetails(): List<OrderDetails> {
    this.firstOrNull { row -> row.order_id == null } ?: return emptyList()

    val details: List<OrderDetails> = this.map { row ->
        return@map OrderDetails(
            orderId = row.order_id ?: "",
            productId = row.product_id ?: "",
            totalPrice = row.total_price ?: 0.0,
            quantity = row.quantity?.toInt() ?: 0,
            name = row.product_name ?: "",
            category = row.category ?: "",
            price = row.price ?: 0.0,
            discount = row.discount ?: 0.0,
            rating = row.rating ?: 0.0,
            images = row.images?.split(",") ?: emptyList(),
        )
    }

    return details
}

package com.bakery.order.data.storage

import com.bakery.core.database.helper.DbHelper
import com.bakery.order.shared.types.CreateOrderDto
import com.bakery.order.shared.types.OrderDto
import com.bakery.order.shared.types.UpdateOrderDto
import kotlinx.coroutines.CoroutineScope

interface OrderStorage {
    suspend fun getOrders(): List<OrderDto>
    suspend fun getOrdersWithLines(): List<OrderDto>
    suspend fun getOrder(id: String): OrderDto?
    suspend fun getOrderWithLines(id: String): OrderDto?
    suspend fun getOrdersByUser(userId: String): List<OrderDto>
    suspend fun getOrdersByUserWithLines(userId: String): List<OrderDto>
    suspend fun createOrder(dto: CreateOrderDto): OrderDto?
    suspend fun updateOrderStatus(dto: UpdateOrderDto): OrderDto?
}

class DefaultOrderStorage(
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : OrderStorage {
    override suspend fun getOrders(): List<OrderDto> {
        return emptyList()
    }

    override suspend fun getOrdersWithLines(): List<OrderDto> {
        return emptyList()
    }

    override suspend fun getOrder(id: String): OrderDto? {
        return null
    }

    override suspend fun getOrderWithLines(id: String): OrderDto? {
        return null
    }

    override suspend fun getOrdersByUser(userId: String): List<OrderDto> {
        return emptyList()
    }

    override suspend fun getOrdersByUserWithLines(userId: String): List<OrderDto> {
        return emptyList()
    }

    override suspend fun createOrder(dto: CreateOrderDto): OrderDto? {
        return null
    }

    override suspend fun updateOrderStatus(dto: UpdateOrderDto): OrderDto? {
        return null
    }
}

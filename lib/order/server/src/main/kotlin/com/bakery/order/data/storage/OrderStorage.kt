package com.bakery.order.data.storage

import com.bakery.core.database.helper.DbHelper
import com.bakery.order.data.mappers.toDto
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
        return dbHelper.withDatabase { db ->
            dbHelper.executeList(
                query = db.bakeryOrderQueries.findAll()
            ).map { order ->
                order.toDto()
            }
        }
    }

    override suspend fun getOrdersWithLines(): List<OrderDto> {
        return dbHelper.withDatabase { db ->
            dbHelper.executeList(
                query = db.bakeryOrderQueries.findAllWithLines()
            ).toDto()
        }
    }

    override suspend fun getOrder(id: String): OrderDto? {
        return dbHelper.withDatabase { db ->
            dbHelper.executeOne(
                query = db.bakeryOrderQueries.findOne(id)
            )?.toDto()
        }
    }

    override suspend fun getOrderWithLines(id: String): OrderDto? {
        return dbHelper.withDatabase { db ->
            dbHelper.executeList(
                query = db.bakeryOrderQueries.findOneWithLines(id)
            ).toDto()
        }
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

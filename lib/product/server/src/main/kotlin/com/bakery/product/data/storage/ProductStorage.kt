package com.bakery.product.data.storage

import com.bakery.core.database.helper.DbHelper
import com.bakery.product.data.mappers.toDb
import com.bakery.product.data.mappers.toDto
import com.bakery.product.shared.types.CreateProductDto
import com.bakery.product.shared.types.ProductDto
import com.bakery.product.shared.types.UpdateProductDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

interface ProductStorage {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getProductById(id: String): ProductDto?
    suspend fun getExistingProducts(): List<ProductDto>
    suspend fun getExistingProductById(id: String): ProductDto?
    suspend fun createProduct(dto: CreateProductDto, images: List<String>): ProductDto?
    suspend fun updateProduct(dto: UpdateProductDto, images: List<String>): ProductDto?
    suspend fun softDeleteProduct(id: String): ProductDto?
    suspend fun deleteProduct(id: String): ProductDto?
}

class DefaultProductStorage(
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : ProductStorage {
    override suspend fun getProducts(): List<ProductDto> {
        return dbHelper.withDatabase { db ->
            executeList(
                query = db.bakeryProductQueries.findAll()
            ).map { product ->
                product.toDto()
            }
        }
    }

    override suspend fun getProductById(id: String): ProductDto? {
        return dbHelper.withDatabase { db ->
            executeOne(
                query = db.bakeryProductQueries.findOne(id)
            )?.toDto()
        }
    }

    override suspend fun getExistingProducts(): List<ProductDto> {
        return dbHelper.withDatabase { db ->
            executeList(
                query = db.bakeryProductQueries.findExisting()
            ).map { product ->
                product.toDto()
            }
        }
    }

    override suspend fun getExistingProductById(id: String): ProductDto? {
        return dbHelper.withDatabase { db ->
            executeOne(
                query = db.bakeryProductQueries.findExistingOne(id)
            )?.toDto()
        }
    }

    override suspend fun createProduct(dto: CreateProductDto, images: List<String>): ProductDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryProductQueries
                        .insert(
                            bakery_product = dto.toDb(images)
                        )
                        .executeAsOneOrNull()
                        ?.toDto()
                }
            }
        }.await()
    }

    override suspend fun updateProduct(dto: UpdateProductDto, images: List<String>): ProductDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryProductQueries
                        .update(
                            name = dto.name,
                            description = dto.description,
                            category = dto.category,
                            price = dto.price,
                            stock = dto.stock.toLong(),
                            has_stock = if (dto.hasStock) 1 else 0,
                            discount = dto.discount,
                            rating = dto.rating,
                            images = images.joinToString(","),
                            id = dto.id
                        )
                        .executeAsOneOrNull()
                        ?.toDto()
                }
            }
        }.await()
    }

    override suspend fun softDeleteProduct(id: String): ProductDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryProductQueries.softDelete(id).executeAsOneOrNull()?.toDto()
                }
            }
        }.await()
    }

    override suspend fun deleteProduct(id: String): ProductDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryProductQueries.delete(id).executeAsOneOrNull()?.toDto()
                }
            }
        }.await()
    }
}

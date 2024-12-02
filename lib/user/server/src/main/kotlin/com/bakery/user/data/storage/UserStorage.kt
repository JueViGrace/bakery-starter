package com.bakery.user.data.storage

import com.bakery.core.database.helper.DbHelper
import com.bakery.user.data.mappers.toDto
import com.bakery.user.shared.types.UpdateUserDto
import com.bakery.user.shared.types.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

interface UserStorage {
    suspend fun getUsers(): List<UserDto>
    suspend fun getUserById(id: String): UserDto?
    suspend fun getExistingUserById(id: String): UserDto?
    suspend fun updateUser(dto: UpdateUserDto): UserDto?
    suspend fun softDeleteUser(id: String): UserDto?
    suspend fun deleteUser(id: String): UserDto?
}

class DefaultUserStorage(
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : UserStorage {
    override suspend fun getUsers(): List<UserDto> {
        return dbHelper.withDatabase { db ->
            dbHelper.executeList(
                query = db.bakeryUserQueries.findAll()
            ).map { user ->
                user.toDto()
            }
        }
    }

    override suspend fun getUserById(id: String): UserDto? {
        return dbHelper.withDatabase { db ->
            dbHelper.executeOne(
                query = db.bakeryUserQueries.findOne(id)
            )?.toDto()
        }
    }

    override suspend fun getExistingUserById(id: String): UserDto? {
        return dbHelper.withDatabase { db ->
            dbHelper.executeOne(
                query = db.bakeryUserQueries.findExistingOne(id)
            )?.toDto()
        }
    }

    override suspend fun updateUser(dto: UpdateUserDto): UserDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    val user = db.bakeryUserQueries
                        .update(
                            first_name = dto.firstName,
                            last_name = dto.lastName,
                            phone_number = dto.phoneNumber,
                            birth_date = dto.birthDate,
                            address1 = dto.address1,
                            address2 = dto.address2,
                            id = dto.id
                        ).executeAsOneOrNull()
                        ?.toDto()
                    if (user == null) {
                        return@transactionWithResult rollback(null)
                    }
                    user
                }
            }
        }.await()
    }

    override suspend fun softDeleteUser(id: String): UserDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryTokenQueries.delete(id)
                    val user = db.bakeryUserQueries.softDelete(id).executeAsOneOrNull()?.toDto()
                    if (user != null) {
                        return@transactionWithResult rollback(user)
                    }
                    null
                }
            }
        }.await()
    }

    override suspend fun deleteUser(id: String): UserDto? {
        return scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    val user = db.bakeryUserQueries.deleteById(id).executeAsOneOrNull()?.toDto()
                    if (user != null) {
                        return@transactionWithResult rollback(user)
                    }
                    null
                }
            }
        }.await()
    }
}

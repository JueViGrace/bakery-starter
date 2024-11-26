package com.bakery.user.data.repository

import com.bakery.core.database.helper.DbHelper
import com.bakery.user.data.mappers.toDto
import com.bakery.user.shared.types.UserDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

interface UserRepository {
    suspend fun getUsers(): List<UserDto>
    suspend fun getUserById(id: String): UserDto?
    suspend fun getUserByUsername(username: String): UserDto?
    suspend fun getUserByEmail(email: String): UserDto?
    suspend fun updateUser(dto: UserDto): UserDto?
    suspend fun softDeleteUser(id: String): UserDto?
    suspend fun deleteUser(id: String): UserDto?
}

class DefaultUserRepository(
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : UserRepository {
    override suspend fun getUsers(): List<UserDto> {
        return scope.async {
            dbHelper.withDatabase { db ->
                dbHelper.executeList(
                    query = db.bakeryUserQueries.findAll()
                ).map { user ->
                    user.toDto()
                }
            }
        }.await()
    }

    override suspend fun getUserById(id: String): UserDto? {
        return null
    }

    override suspend fun getUserByUsername(username: String): UserDto? {
        return null
    }

    override suspend fun getUserByEmail(email: String): UserDto? {
        return null
    }

    override suspend fun updateUser(dto: UserDto): UserDto? {
        return null
    }

    override suspend fun softDeleteUser(id: String): UserDto? {
        return null
    }

    override suspend fun deleteUser(id: String): UserDto? {
        return null
    }
}

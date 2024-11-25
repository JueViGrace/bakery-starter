package com.bakery.user.server.data.repository

import com.bakery.core.shared.database.helper.DbHelper
import com.bakery.user.shared.types.UserDto
import kotlinx.coroutines.CoroutineScope

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
    private val db: DbHelper
) : UserRepository {
    override suspend fun getUsers(): List<UserDto> {
        return emptyList()
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

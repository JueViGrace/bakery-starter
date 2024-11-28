package com.bakery.user.data.handler

import com.bakery.core.types.APIResponse
import com.bakery.core.types.ServerResponse
import com.bakery.user.data.repository.UserRepository
import com.bakery.user.shared.types.UserDto
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface UserHandler {
    suspend fun getUsers(): APIResponse<List<UserDto>>
    suspend fun getUserById(id: String): APIResponse<UserDto?>
    suspend fun getUserByUsername(username: String): APIResponse<UserDto?>
    suspend fun getUserByEmail(email: String): APIResponse<UserDto?>
    suspend fun updateUser(dto: UserDto): APIResponse<UserDto?>
    suspend fun softDeleteUser(id: String): APIResponse<UserDto?>
    suspend fun deleteUser(id: String): APIResponse<UserDto?>
}

class DefaultUserHandler(
    private val coroutineContext: CoroutineContext,
    private val repository: UserRepository
) : UserHandler {
    override suspend fun getUsers(): APIResponse<List<UserDto>> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.getUsers(), message = "Processed successfully")
        }
    }

    override suspend fun getUserById(id: String): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.getUserById(id), message = "Processed successfully")
        }
    }

    override suspend fun getUserByUsername(username: String): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.getUserByUsername(username), message = "Processed successfully")
        }
    }

    override suspend fun getUserByEmail(email: String): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.getUserByEmail(email), message = "Processed successfully")
        }
    }

    override suspend fun updateUser(dto: UserDto): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.updateUser(dto), message = "Processed successfully")
        }
    }

    override suspend fun softDeleteUser(id: String): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.softDeleteUser(id), message = "Processed successfully")
        }
    }

    override suspend fun deleteUser(id: String): APIResponse<UserDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.deleteUser(id), message = "Processed successfully")
        }
    }
}

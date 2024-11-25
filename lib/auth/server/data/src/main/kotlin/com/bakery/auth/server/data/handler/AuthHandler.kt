package com.bakery.auth.server.data.handler

import com.bakery.auth.server.data.repository.AuthRepository
import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.server.types.ServerResponse
import com.bakery.core.shared.types.APIResponse
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

interface AuthHandler {
    suspend fun signIn(dto: SignInDto): APIResponse<AuthDto?>
    suspend fun signUp(dto: SignUpDto): APIResponse<AuthDto?>
    suspend fun refresh(dto: RefreshTokenDto): APIResponse<AuthDto?>
    suspend fun forgotPassword(dto: ForgotPasswordDto): APIResponse<String>
}

// todo: make validations
class DefaultAuthHandler(
    private val coroutineContext: CoroutineContext,
    private val repository: AuthRepository
) : AuthHandler {
    override suspend fun signIn(dto: SignInDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.signIn(dto), message = "Processed successfully")
        }
    }

    override suspend fun signUp(dto: SignUpDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            ServerResponse.created(data = repository.signUp(dto), message = "Processed successfully")
        }
    }

    override suspend fun refresh(dto: RefreshTokenDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.refresh(dto), message = "Processed successfully")
        }
    }

    override suspend fun forgotPassword(dto: ForgotPasswordDto): APIResponse<String> {
        return withContext(coroutineContext) {
            ServerResponse.ok(data = repository.forgotPassword(dto), message = "Processed successfully")
        }
    }
}

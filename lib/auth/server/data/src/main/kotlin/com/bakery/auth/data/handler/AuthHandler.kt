package com.bakery.auth.data.handler

import com.bakery.auth.data.repository.AuthRepository
import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.types.APIResponse
import com.bakery.core.types.ServerResponse
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

//todo: check for conflicts
interface AuthHandler {
    suspend fun signIn(dto: SignInDto): APIResponse<AuthDto?>
    suspend fun signUp(dto: SignUpDto): APIResponse<AuthDto?>
    suspend fun refresh(dto: RefreshTokenDto): APIResponse<AuthDto?>
    suspend fun forgotPassword(dto: ForgotPasswordDto): APIResponse<AuthDto?>
}

class DefaultAuthHandler(
    private val coroutineContext: CoroutineContext,
    private val repository: AuthRepository
) : AuthHandler {
    override suspend fun signIn(dto: SignInDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            val result = repository.getValidUser(dto)

            if (result == null) {
                return@withContext ServerResponse.badRequest(
                    message = "Invalid credentials"
                )
            }

            return@withContext ServerResponse.ok(
                data = result,
                message = "Processed successfully"
            )
        }
    }

    override suspend fun signUp(dto: SignUpDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            val result = repository.signUp(dto)

            if (result == null) {
                return@withContext ServerResponse.badRequest(
                    message = "Unable to create user, try again later."
                )
            }

            ServerResponse.created(
                data = result,
                message = "Processed successfully"
            )
        }
    }

    override suspend fun refresh(dto: RefreshTokenDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            val result = repository.refresh(dto)

            if (result == null) {
                return@withContext ServerResponse.badRequest(
                    message = "Invalid refresh token"
                )
            }

            return@withContext ServerResponse.ok(
                data = result,
                message = "Processed successfully"
            )
        }
    }

    // todo: do this right
    override suspend fun forgotPassword(dto: ForgotPasswordDto): APIResponse<AuthDto?> {
        return withContext(coroutineContext) {
            val result = repository.forgotPassword(dto)

            if (result == null) {
                return@withContext ServerResponse.badRequest(
                    message = "Unable to process change of password, try again."
                )
            }

            return@withContext ServerResponse.ok(
                data = result,
                message = "Processed successfully"
            )
        }
    }
}

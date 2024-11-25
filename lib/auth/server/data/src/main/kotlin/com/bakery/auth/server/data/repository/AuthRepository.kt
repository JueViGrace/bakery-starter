package com.bakery.auth.server.data.repository

import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.shared.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope

interface AuthRepository {
    suspend fun signIn(dto: SignInDto): AuthDto?
    suspend fun signUp(dto: SignUpDto): AuthDto?
    suspend fun refresh(dto: RefreshTokenDto): AuthDto?
    suspend fun forgotPassword(dto: ForgotPasswordDto): String
}

class DefaultAuthRepository(
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : AuthRepository {
    override suspend fun signIn(dto: SignInDto): AuthDto? {
        return null
    }

    override suspend fun signUp(dto: SignUpDto): AuthDto? {
        return null
    }

    override suspend fun refresh(dto: RefreshTokenDto): AuthDto? {
        return null
    }

    override suspend fun forgotPassword(dto: ForgotPasswordDto): String {
        return ""
    }
}

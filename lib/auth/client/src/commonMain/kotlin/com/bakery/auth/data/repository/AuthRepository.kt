package com.bakery.auth.data.repository

import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.api.KtorClient
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.types.Repository
import com.bakery.core.types.state.RequestState
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

interface AuthRepository : Repository {
    suspend fun signIn(signInDto: SignInDto): RequestState<AuthDto>
    suspend fun signUp(signUpDto: SignUpDto): RequestState<AuthDto>
    suspend fun forgotPassword(forgotPasswordDto: ForgotPasswordDto): RequestState<AuthDto?>
}

class DefaultAuthRepository(
    private val ktorClient: KtorClient,
    private val dbHelper: DbHelper,
    override val coroutineContext: CoroutineContext,
    override val scope: CoroutineScope,
) : AuthRepository {
    override suspend fun signIn(signInDto: SignInDto): RequestState<AuthDto> {

    }

    override suspend fun signUp(signUpDto: SignUpDto): RequestState<AuthDto> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(forgotPasswordDto: ForgotPasswordDto): RequestState<AuthDto?> {
        TODO("Not yet implemented")
    }
}

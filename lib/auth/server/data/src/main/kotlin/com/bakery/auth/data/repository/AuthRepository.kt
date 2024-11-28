package com.bakery.auth.data.repository

import com.bakery.auth.data.mappers.toDb
import com.bakery.auth.shared.types.AuthDto
import com.bakery.auth.shared.types.ForgotPasswordDto
import com.bakery.auth.shared.types.RefreshTokenDto
import com.bakery.auth.shared.types.SignInDto
import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.database.Bakery_token
import com.bakery.core.database.Bakery_user
import com.bakery.core.database.helper.DbHelper
import com.bakery.core.util.Jwt
import com.bakery.core.util.Kbcrypt
import com.bakery.core.util.Util.verifyEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

interface AuthRepository {
    suspend fun getValidUser(dto: SignInDto): AuthDto?
    suspend fun signUp(dto: SignUpDto): AuthDto?
    suspend fun refresh(dto: RefreshTokenDto): AuthDto?
    suspend fun forgotPassword(dto: ForgotPasswordDto): AuthDto?
    suspend fun saveToken(userId: String): AuthDto?
}

class DefaultAuthRepository(
    private val jwt: Jwt,
    private val scope: CoroutineScope,
    private val dbHelper: DbHelper
) : AuthRepository {
    private suspend fun findUserByUsername(username: String): Bakery_user? {
        return dbHelper.withDatabase { db ->
            db.transactionWithResult {
                executeOne(
                    query = db.bakeryUserQueries.findOneByUsername(username)
                )
            }
        }
    }

    private suspend fun findUserByEmail(email: String): Bakery_user? {
        return dbHelper.withDatabase { db ->
            db.transactionWithResult {
                executeOne(
                    query = db.bakeryUserQueries.findOneByEmail(email)
                )
            }
        }
    }

    override suspend fun getValidUser(dto: SignInDto): AuthDto? {
        val dbUser = if (!verifyEmail(dto.username)) {
            findUserByUsername(dto.username)
        } else {
            findUserByEmail(dto.username)
        }

        if (dbUser == null || !Kbcrypt.verifyPassword(dto.password, dbUser.password)) {
            return null
        }

        return saveToken(
            userId = dbUser.id,
        )
    }

    override suspend fun signUp(dto: SignUpDto): AuthDto? {
        val dbUser = scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryUserQueries.insert(dto.toDb()).executeAsOneOrNull()
                }
            }
        }.await()

        if (dbUser == null) {
            return null
        }

        return saveToken(
            userId = dbUser.id,
        )
    }

    // todo: delete old token?
    override suspend fun refresh(dto: RefreshTokenDto): AuthDto? {
        val token = jwt.verifyToken(dto.refreshToken)
        if (token == null) {
            return null
        }

        return saveToken(
            userId = token.userId,
        )
    }

    override suspend fun forgotPassword(dto: ForgotPasswordDto): AuthDto? {
        return null
    }

    override suspend fun saveToken(
        userId: String,
    ): AuthDto? {
        val accessToken = jwt.createAccessToken(
            claims = mapOf(
                "user_id" to userId,
            )
        )
        val refreshToken = jwt.createRefreshToken(
            claims = mapOf(
                "user_id" to userId,
            )
        )

        scope.async {
            dbHelper.withDatabase { db ->
                db.transactionWithResult {
                    db.bakeryTokenQueries.insert(
                        bakery_token = Bakery_token(
                            token = refreshToken,
                            user_id = userId
                        )
                    ).executeAsOneOrNull()
                }
            }
        }.await() ?: return null

        return AuthDto(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}

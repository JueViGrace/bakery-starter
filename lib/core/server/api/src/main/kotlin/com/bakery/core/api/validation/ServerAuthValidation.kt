package com.bakery.core.api.validation

import com.bakery.core.database.helper.DbHelper
import com.bakery.core.types.JwtAuthName
import com.bakery.core.types.Role
import com.bakery.core.types.UserIdValidation
import com.bakery.core.util.Jwt
import com.bakery.user.validation.userAuth
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt

fun AuthenticationConfig.serverAuthValidation(jwt: Jwt, dbHelper: DbHelper) {
    jwt(JwtAuthName.SESSION.value) {
        realm = jwt.realm

        verifier(jwt.jwtVerifier)

        validate { credential ->
            jwt.validateCredential(credential) {
                extractId(credential)?.let { id ->
                    dbHelper.withDatabase { db ->
                        executeOne(db.bakeryTokenQueries.findById(id))
                    }
                } ?: return@validateCredential null

                JWTPrincipal(credential.payload)
            }
        }
    }

    userAuth(
        name = JwtAuthName.USER.value,
        jwt = jwt
    ) { id ->
        val dbUser = dbHelper.withDatabase { db ->
            executeOne(
                query = db.bakeryUserQueries.findOne(id)
            )
        }

        if (dbUser == null) {
            return@userAuth null
        }

        UserIdValidation(
            isAdmin = dbUser.role == Role.ADMIN.value,
            userId = dbUser.id
        )
    }

    jwt(JwtAuthName.ADMIN.value) {
        realm = jwt.realm

        verifier(jwt.jwtVerifier)

        validate { credential ->
            jwt.validateCredential(credential) {
                val user = extractId(credential)?.let { id ->
                    dbHelper.withDatabase { db ->
                        executeOne(db.bakeryUserQueries.findOne(id))
                    }
                }

                if (user == null) {
                    return@validateCredential null
                }

                if (user.role == Role.ADMIN.value) {
                    return@validateCredential JWTPrincipal(credential.payload)
                }

                null
            }
        }
    }
}

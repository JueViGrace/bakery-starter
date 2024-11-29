package com.bakery.user.validation

import com.bakery.core.types.UserIdValidation
import com.bakery.core.util.Jwt
import com.bakery.user.shared.types.UserByIdDto
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.request.receive

fun AuthenticationConfig.userAuth(
    name: String,
    jwt: Jwt,
    userCall: suspend (String) -> UserIdValidation?
) {
    jwt(name = name) {
        realm = jwt.realm

        verifier(jwt.jwtVerifier)

        validate { credential ->
            jwt.validateCredential(credential) {
                val tokenId = extractId(credential)

                if (tokenId == null) {
                    return@validateCredential null
                }

                val user = userCall(tokenId)

                if (user == null) {
                    return@validateCredential null
                }

                if (user.isAdmin) {
                    return@validateCredential JWTPrincipal(credential.payload)
                }

                val idParam = receive<UserByIdDto>()

                if (user.userId != idParam.id) {
                    return@validateCredential null
                }

                JWTPrincipal(credential.payload)
            }
        }
    }
}

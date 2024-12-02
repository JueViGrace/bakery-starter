package com.bakery.order.validation

import com.bakery.core.types.OrderDataValidation
import com.bakery.core.types.UserIdValidation
import com.bakery.core.util.Jwt
import com.bakery.order.shared.types.CancelOrderDto
import com.bakery.order.shared.types.CreateOrderDto
import com.bakery.order.shared.types.OrderByUserDto
import com.bakery.order.shared.types.OrdersByUserDto
import io.ktor.server.auth.AuthenticationConfig
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.request.receive
import kotlinx.serialization.json.Json

fun AuthenticationConfig.ordersAuth(
    name: String,
    jwt: Jwt,
    userCall: suspend (String) -> UserIdValidation?,
    orderCall: suspend (String) -> OrderDataValidation?
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

                when (val body: Any = Json.decodeFromString(receive<String>())) {
                    is OrdersByUserDto -> {
                        if (user.userId != body.userId) {
                            return@validateCredential null
                        }
                    }
                    is OrderByUserDto -> {
                        if (user.userId != body.userId) {
                            return@validateCredential null
                        }
                    }
                    is CreateOrderDto -> {
                        if (user.userId != body.userId) {
                            return@validateCredential null
                        }
                    }
                    is CancelOrderDto -> {
                        val order = orderCall(body.id)
                        if (order == null) {
                            return@validateCredential null
                        }

                        if (user.userId != order.userId) {
                            return@validateCredential null
                        }
                    }
                }

                JWTPrincipal(credential.payload)
            }
        }
    }
}

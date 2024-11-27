package com.bakery.server.config

import com.bakery.core.database.helper.DbHelper
import com.bakery.core.types.Role
import com.bakery.core.util.Jwt
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import org.koin.core.parameter.parametersOf
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    // todo: idk if this should be here
    val dbHelper: DbHelper by inject<DbHelper>()
    val jwt: Jwt by inject<Jwt>(
        parameters = {
            parametersOf(
                environment.config.property("ktor.jwt.secret").getString(),
                environment.config.property("ktor.jwt.issuer").getString(),
                environment.config.property("ktor.jwt.audience").getString(),
                environment.config.property("ktor.jwt.realm").getString(),
            )
        }
    )

    install(Authentication) {
        jwt("session-auth") {
            realm = jwt.realm

            verifier(jwt.jwtVerifier)

            validate { credential ->
                jwt.validateCredential(credential) {
                    extractId(credential)?.let { id ->
                        dbHelper.withDatabase { db ->
                            executeOne(db.bakeryUserQueries.findOne(id))
                        }
                    } ?: return@validateCredential null

                    JWTPrincipal(credential.payload)
                }
            }
        }

        jwt("role-auth") {
            realm = jwt.realm

            verifier(jwt.jwtVerifier)

            validate { credential ->
                jwt.validateCredential(credential) {
                    if (extractRole(credential) != Role.ADMIN.value) {
                        return@validateCredential null
                    }

                    val user = extractId(credential)?.let { id ->
                        dbHelper.withDatabase { db ->
                            executeOne(db.bakeryUserQueries.findOne(id))
                        }
                    } ?: return@validateCredential null

                    if (user.role != Role.ADMIN.value) {
                        return@validateCredential null
                    }

                    if (user.role != extractRole(credential)) {
                        return@validateCredential null
                    }

                    JWTPrincipal(credential.payload)
                }
            }
        }
    }
}

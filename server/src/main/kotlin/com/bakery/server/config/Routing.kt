package com.bakery.server.config

import com.bakery.core.server.types.ServerResponse.badRequest
import com.bakery.core.server.types.ServerResponse.internalServerError
import com.bakery.core.server.types.applicationResponse
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.path
import io.ktor.server.routing.routing
import io.ktor.server.sse.SSE
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")

    install(StatusPages) {
        exception<Throwable> { call: ApplicationCall, cause: Throwable ->
            logger.error("Unhandled error at: ${call.request.path()}", cause)
            call.applicationResponse(
                response = internalServerError(
                    data = "Something unexpected happened, try again later.",
                    message = cause.message
                )
            )
        }

        exception<RequestValidationException> { call, cause ->
            logger.error("Validation error", cause)
            call.applicationResponse(
                response = badRequest(
                    data = "Validation error",
                    message = cause.reasons.joinToString(",")
                )
            )
        }
    }

    install(SSE)

    routing {
        staticResources("/static", "static")
    }
}

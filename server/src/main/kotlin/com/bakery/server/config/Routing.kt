package com.bakery.server.config

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.path
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")

    install(StatusPages) {
        exception<Throwable> { call: ApplicationCall, cause: Throwable ->
            logger.error("Unhandled error at: ${call.request.path()}", cause)
            // todo: respond
        }

        exception<RequestValidationException> { call, cause ->
            logger.error("Validation error", cause)
            // todo: respond
        }
    }
}

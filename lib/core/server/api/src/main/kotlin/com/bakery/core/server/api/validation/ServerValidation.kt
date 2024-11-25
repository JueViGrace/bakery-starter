package com.bakery.core.server.api.validation

import com.bakery.auth.server.validation.authValidation
import com.bakery.user.server.validation.userValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig

fun RequestValidationConfig.serverValidation() {
    authValidation()
    userValidation()
}

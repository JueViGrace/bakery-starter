package com.bakery.core.api.validation

import com.bakery.auth.validation.authValidation
import com.bakery.user.validation.userValidation
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig

fun RequestValidationConfig.serverValidation() {
    authValidation()
    userValidation()
}

package com.bakery.auth.server.validation

import io.ktor.server.plugins.requestvalidation.RequestValidationConfig

fun RequestValidationConfig.authValidation() {
    validateSignInDto()
    validateSignUpDto()
}


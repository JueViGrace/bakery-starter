package com.bakery.user.server.validation

import io.ktor.server.plugins.requestvalidation.RequestValidationConfig

fun RequestValidationConfig.userValidation() {
    validateUserDto()
}

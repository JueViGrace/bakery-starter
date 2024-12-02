package com.bakery.order.validation

import com.bakery.core.util.Util.validUuid
import com.bakery.order.shared.types.OrdersByUserDto
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateOrderByUser() {
    validate<OrdersByUserDto> { dto ->
        when {
            dto.userId.isBlank() -> ValidationResult.Invalid("Id cannot be blank")
            !validUuid(dto.userId) -> ValidationResult.Invalid("Id is not valid")
            else -> ValidationResult.Valid
        }
    }
}
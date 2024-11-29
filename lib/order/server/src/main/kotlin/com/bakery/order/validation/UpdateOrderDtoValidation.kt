package com.bakery.order.validation

import com.bakery.core.util.Util.validUuid
import com.bakery.order.shared.types.UpdateOrderDto
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateUpdateOrderDto() {
    validate<UpdateOrderDto> { dto ->
        when {
            dto.id.isBlank() -> ValidationResult.Invalid("Id cannot be blank")
            !validUuid(dto.id) -> ValidationResult.Invalid("Id is not valid")
            dto.status.isBlank() -> ValidationResult.Invalid("Status cannot be blank")
            else -> ValidationResult.Valid
        }
    }
}

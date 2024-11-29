package com.bakery.user.validation

import com.bakery.core.util.Util.validUuid
import com.bakery.user.shared.types.UpdateUserDto
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun RequestValidationConfig.validateUpdateUserDto() {
    validate<UpdateUserDto> { dto ->
        when {
            !validUuid(dto.id) -> ValidationResult.Invalid("Invalid id.")
            dto.firstName.isEmpty() -> ValidationResult.Invalid("First name cannot be empty")
            dto.lastName.isEmpty() -> ValidationResult.Invalid("Last name cannot be empty")
            dto.phoneNumber.isEmpty() -> ValidationResult.Invalid("Phone number cannot be empty")
            dto.birthDate.isEmpty() -> ValidationResult.Invalid("Birth date cannot be empty")
            dto.address1.isEmpty() -> ValidationResult.Invalid("Address 1 cannot be empty")
            dto.address2.isEmpty() -> ValidationResult.Invalid("Address 2 cannot be empty")
            else -> ValidationResult.Valid
        }
    }
}

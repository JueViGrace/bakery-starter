package com.bakery.user.validation

import com.bakery.user.shared.types.UserDto
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

internal fun RequestValidationConfig.validateUserDto() {
    validate<UserDto> { dto ->
        when {
            dto.firstName.isEmpty() -> ValidationResult.Invalid("First name cannot be empty")
            dto.lastName.isEmpty() -> ValidationResult.Invalid("Last name cannot be empty")
            dto.username.isEmpty() -> ValidationResult.Invalid("Username cannot be empty")
            dto.email.isEmpty() -> ValidationResult.Invalid("Email cannot be empty")
            dto.phoneNumber.isEmpty() -> ValidationResult.Invalid("Phone number cannot be empty")
            dto.birthDate.isEmpty() -> ValidationResult.Invalid("Birth date cannot be empty")
            dto.address1.isEmpty() -> ValidationResult.Invalid("Address 1 cannot be empty")
            dto.address2.isEmpty() -> ValidationResult.Invalid("Address 2 cannot be empty")
            else -> ValidationResult.Valid
        }
    }
}

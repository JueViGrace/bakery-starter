package com.bakery.auth.server.validation

import com.bakery.auth.shared.types.SignUpDto
import com.bakery.core.shared.types.Constants
import io.ktor.server.plugins.requestvalidation.RequestValidationConfig
import io.ktor.server.plugins.requestvalidation.ValidationResult

internal fun RequestValidationConfig.validateSignUpDto() {
    validate<SignUpDto> { dto ->
        when {
            dto.firstName.isBlank() -> ValidationResult.Invalid("First name cannot be blank")
            dto.firstName.length < Constants.MINIMUM_LENGTH -> {
                ValidationResult.Invalid("First name must be longer than 4 characters")
            }
            dto.lastName.isBlank() -> ValidationResult.Invalid("Last name cannot be blank")
            dto.lastName.length < Constants.MINIMUM_LENGTH -> {
                ValidationResult.Invalid("Last name must be longer than 4 characters")
            }
            dto.username.isBlank() -> ValidationResult.Invalid("Username cannot be blank")
            dto.username.length < Constants.MINIMUM_LENGTH -> {
                ValidationResult.Invalid("Username must be longer than 4 characters")
            }
            dto.email.isBlank() -> ValidationResult.Invalid("Email cannot be blank")
            dto.password.isBlank() -> ValidationResult.Invalid("Password cannot be blank")
            dto.password.length < Constants.MINIMUM_LENGTH -> {
                ValidationResult.Invalid("Password must be longer than 4 characters")
            }
            dto.phoneNumber.isBlank() -> ValidationResult.Invalid("Phone number cannot be blank")
            dto.birthDate.isBlank() -> ValidationResult.Invalid("Birthday cannot be blank")
            dto.address1.isBlank() -> ValidationResult.Invalid("Address cannot be blank")
            dto.address2.isBlank() -> ValidationResult.Invalid("Address cannot be blank")
            else -> ValidationResult.Valid
        }
    }
}

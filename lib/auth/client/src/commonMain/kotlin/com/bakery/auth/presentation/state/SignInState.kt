package com.bakery.auth.presentation.state

import org.jetbrains.compose.resources.StringResource

data class SignInState(
    // Field values
    val username: String = "",
    val password: String = "",

    // Validation messages
    val usernameError: StringResource? = null,
    val passwordError: StringResource? = null,
)

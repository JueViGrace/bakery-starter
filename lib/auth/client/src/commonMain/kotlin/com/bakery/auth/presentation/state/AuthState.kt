package com.bakery.auth.presentation.state

import org.jetbrains.compose.resources.StringResource

data class AuthState(
    // Screen state
    val isSignIn: Boolean = true,

    // Sign in screen state
    val signInState: SignInState = SignInState(),

    // Sign up screen state
    val signUpState: SignUpState = SignUpState(),

    // Screen states
    val isLoading: Boolean = false,

    // Error message
    val errorMessage: StringResource? = null,
)

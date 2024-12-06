package com.bakery.auth.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.SignUpState

@Composable
expect fun SignUpScreen(
    state: SignUpState,
    onEvent: (AuthEvents) -> Unit,
)

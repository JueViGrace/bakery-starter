package com.bakery.auth.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.SignInState

@Composable
expect fun SignInScreen(
    state: SignInState,
    onEvent: (AuthEvents) -> Unit,
)

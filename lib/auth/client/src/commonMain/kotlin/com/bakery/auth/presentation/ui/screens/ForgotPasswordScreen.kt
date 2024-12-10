package com.bakery.auth.presentation.ui.screens

import androidx.compose.runtime.Composable
import com.bakery.auth.presentation.viewmodel.ForgotPasswordViewModel
import org.koin.compose.koinInject

@Composable
expect fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinInject()
)

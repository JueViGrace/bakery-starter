package com.bakery.app.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bakery.auth.presentation.screens.ForgotPasswordScreen
import com.bakery.auth.presentation.screens.LoginScreen
import com.bakery.core.presentation.navigation.Destination

fun NavGraphBuilder.authGraph() {
    navigation<Destination.AuthGraph>(
        startDestination = Destination.Login
    ) {
        composable<Destination.Login> {
            LoginScreen()
        }

        composable<Destination.ForgotPassword> {
            ForgotPasswordScreen()
        }
    }
}
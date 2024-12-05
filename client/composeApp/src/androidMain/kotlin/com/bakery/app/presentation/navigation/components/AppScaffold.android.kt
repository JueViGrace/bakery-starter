package com.bakery.app.presentation.navigation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bakery.app.presentation.navigation.graph.authGraph
import com.bakery.app.presentation.navigation.graph.homeGraph
import com.bakery.app.splash.presentation.ui.screen.SplashScreen
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.Navigator

@Composable
actual fun AppScaffold(
    modifier: Modifier,
    navController: NavHostController,
    navigator: Navigator,
    snackBarHostState: SnackbarHostState,
) {
    val currentDestination by navigator.currentDestination.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            if (currentDestination == Destination.HomeGraph) {
            }
        },
        bottomBar = {
            if (currentDestination == Destination.HomeGraph) {

            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = navigator.startDestination,
        ) {
            composable<Destination.Splash> {
                SplashScreen()
            }
            authGraph()
            homeGraph()
        }
    }
}

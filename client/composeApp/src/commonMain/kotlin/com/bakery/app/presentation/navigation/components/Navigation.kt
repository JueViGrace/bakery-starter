package com.bakery.app.presentation.navigation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bakery.app.presentation.navigation.graph.authGraph
import com.bakery.app.presentation.navigation.graph.homeGraph
import com.bakery.app.presentation.viewmodel.AppViewModel
import com.bakery.app.splash.presentation.ui.screen.SplashScreen
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.NavigationAction
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.presentation.navigation.ObserveAsEvents
import com.bakery.core.shared.types.Constants.SNACK_BAR_MESSAGE_KEY
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    appViewModel: AppViewModel = koinViewModel()
) {
    val navigator = koinInject<Navigator>()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(
        flow = navigator.navigationActions,
    ) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(action.destination)
            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    ObserveAsEvents(
        flow = navigator.stateHandle.getStateFlow(SNACK_BAR_MESSAGE_KEY, ""),
    ) { msg ->
        println(msg)
        if (msg.isEmpty()) return@ObserveAsEvents
        scope.launch {
            snackBarHostState.showSnackbar(msg)
        }
    }

    Scaffold(
        topBar = {
        },
        bottomBar = {
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

package com.bakery.app.presentation.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.bakery.app.presentation.navigation.graph.authGraph
import com.bakery.app.presentation.navigation.graph.homeGraph
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.NavigationAction
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.presentation.navigation.ObserveAsEvents
import org.koin.compose.koinInject

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(
        flow = navigator.navigationActions,
    ) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(action.destination)
            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = navigator.startDestination,
    ) {
        composable<Destination.Splash> {

        }
        authGraph()
        homeGraph()
    }
}

package com.bakery.app.presentation.navigation.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bakery.app.presentation.viewmodel.AppViewModel
import com.bakery.core.presentation.navigation.NavigationAction
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.presentation.navigation.ObserveAsEvents
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
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
            is NavigationAction.Navigate -> navController.navigate(action.destination, builder = action.navOptions)
            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    ObserveAsEvents(
        flow = appViewModel.state,
    ) { msg ->
        if (msg.snackMessage == null) return@ObserveAsEvents
        scope.launch {
            snackBarHostState.showSnackbar(getString(msg.snackMessage))
        }
    }

    AppScaffold(
        modifier = modifier,
        navController = navController,
        navigator = navigator,
        snackBarHostState = snackBarHostState
    )
}

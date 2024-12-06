package com.bakery.core.presentation.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

interface Navigator {
    val startDestination: Destination
    val navigationActions: Flow<NavigationAction>
    val currentDestination: StateFlow<Destination?>
    val stateHandle: SavedStateHandle

    suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}

class DefaultNavigator(
    override val startDestination: Destination,
    override val stateHandle: SavedStateHandle,
) : Navigator {
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions: Flow<NavigationAction> = _navigationActions.receiveAsFlow()

    private val _currentDestination = MutableStateFlow(startDestination)
    override val currentDestination: StateFlow<Destination> = _currentDestination.asStateFlow()

    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit,
    ) {
        _currentDestination.update { destination }
        _navigationActions.send(NavigationAction.Navigate(destination, navOptions))
    }

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }
}

package com.bakery.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.app.data.AppRepository
import com.bakery.app.presentation.state.AppState
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.welcome_back
import com.bakery.core.types.state.RequestState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AppViewModel(
    navigator: Navigator,
    appRepository: AppRepository,
) : ViewModel() {
    private var _state = MutableStateFlow(AppState())
    val state = combine(
        _state,
        appRepository.validateSession()
    ) { state, session ->
        when (session) {
            is RequestState.Error -> {
                delay(1000)
                navigator.navigate(
                    destination = Destination.AuthGraph,
                    navOptions = {
                        popUpTo(Destination.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
                state.copy(
                    session = null,
                    snackMessage = session.error.message
                )
            }
            is RequestState.Success -> {
                delay(1000)
                navigator.navigate(
                    destination = Destination.HomeGraph,
                    navOptions = {
                        popUpTo(Destination.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
                state.copy(
                    session = session.data,
                    snackMessage = Res.string.welcome_back,
                )
            }
            else -> {
                state.copy(
                    session = null,
                    snackMessage = null
                )
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AppState()
    )
}

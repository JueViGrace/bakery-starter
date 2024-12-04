package com.bakery.app.splash.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.app.splash.data.SplashRepository
import com.bakery.app.splash.presentation.ui.state.SplashState
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.Navigator
import com.bakery.core.shared.types.Constants.SNACK_BAR_MESSAGE_KEY
import com.bakery.core.types.common.unwrapResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// check here for user session, and maybe refresh tokens?
class SplashViewModel(
    navigator: Navigator,
    private val stateHandle: SavedStateHandle,
    private val splashRepository: SplashRepository,
) : ViewModel() {
    private var _state: MutableStateFlow<SplashState> = MutableStateFlow(SplashState())
    val state: StateFlow<SplashState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // this is causing the trouble
//            validateSession()
//            refresh()

            stateHandle[SNACK_BAR_MESSAGE_KEY] = "hola"

            if (_state.value.session != null && !_state.value.isLoading) {
                navigator.navigate(
                    destination = Destination.HomeGraph,
                    navOptions = {
                        popUpTo(Destination.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            }

            if (_state.value.session == null && !_state.value.isLoading) {
                navigator.navigate(
                    destination = Destination.AuthGraph,
                    navOptions = {
                        popUpTo(Destination.AuthGraph) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            }
        }
    }

    private suspend fun validateSession() {
        splashRepository.validateSession().unwrapResult(
            onLoading = {
                _state.update { state ->
                    state.copy(
                        session = null,
                        message = null
                    )
                }
            },
            onError = { code ->
                _state.update { state ->
                    state.copy(
                        session = null,
                        message = code.message
                    )
                }
            },
            onSuccess = { session ->
                _state.update { state ->
                    state.copy(
                        session = session,
                        message = null,
                    )
                }
            }
        )
    }

    private suspend fun refresh() {
        splashRepository.refresh().unwrapResult(
            onError = { code ->
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        message = code.message
                    )
                }
            },
            onLoading = {
                _state.update { state ->
                    state.copy(
                        isLoading = true,
                        message = null
                    )
                }
            },
            onSuccess = { code ->
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        message = code.message
                    )
                }
            }
        )
    }
}

package com.bakery.app.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakery.core.presentation.navigation.Destination
import com.bakery.core.presentation.navigation.Navigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// check here for user session, and maybe refresh tokens?
class SplashViewModel(
    navigator: Navigator,
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(3000)
            navigator.navigate(Destination.AuthGraph)
        }
    }
}

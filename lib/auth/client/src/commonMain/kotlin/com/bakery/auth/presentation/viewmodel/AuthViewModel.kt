package com.bakery.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bakery.auth.data.repository.AuthRepository
import com.bakery.core.presentation.navigation.Navigator

class AuthViewModel(
    navigator: Navigator,
    private val authRepository: AuthRepository
) : ViewModel() {

}

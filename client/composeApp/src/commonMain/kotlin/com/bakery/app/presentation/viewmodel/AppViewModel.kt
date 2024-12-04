package com.bakery.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bakery.core.shared.types.Constants.SNACK_BAR_MESSAGE_KEY

class AppViewModel(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val snackState = stateHandle.getStateFlow(SNACK_BAR_MESSAGE_KEY, "")
}

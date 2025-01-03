package com.bakery.order.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.bakery.order.presentation.state.OrderDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OrderDetailsViewModel : ViewModel() {
    private val _state = MutableStateFlow(OrderDetailsState())
    val state = _state.asStateFlow()
}

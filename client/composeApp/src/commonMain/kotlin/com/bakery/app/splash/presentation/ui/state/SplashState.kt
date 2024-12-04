package com.bakery.app.splash.presentation.ui.state

import com.bakery.core.types.Session
import org.jetbrains.compose.resources.StringResource

data class SplashState(
    val session: Session? = null,
    val isLoading: Boolean = true,
    val message: StringResource? = null
)

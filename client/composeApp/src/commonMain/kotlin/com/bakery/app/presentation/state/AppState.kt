package com.bakery.app.presentation.state

import com.bakery.core.types.Session
import org.jetbrains.compose.resources.StringResource

data class AppState(
    val session: Session? = null,
    val snackMessage: StringResource? = null
)

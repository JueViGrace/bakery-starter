package com.bakery.auth.presentation.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.SignUpState
import com.bakery.core.presentation.ui.components.display.TextComponent
import com.bakery.core.presentation.ui.components.layout.mobile.MobileAuthMenu
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.welcome
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun SignUpScreen(
    state: SignUpState,
    onEvent: (AuthEvents) -> Unit,
) {
    MobileAuthMenu(
        title = {
            TextComponent(
                text = stringResource(Res.string.welcome),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                textAlign = MaterialTheme.typography.titleLarge.textAlign,
            )
        },
        content = {},
        footer = {}
    )
}

package com.bakery.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.SignInState
import com.bakery.core.presentation.ui.components.display.CustomText
import com.bakery.core.presentation.ui.components.layout.mobile.MobileAuthMenu
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.log_in
import com.bakery.core.resources.resources.generated.resources.welcome_back
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun SignInScreen(
    state: SignInState,
    onEvent: (AuthEvents) -> Unit,
) {
    MobileAuthMenu(
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = stringResource(Res.string.welcome_back),
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                    textAlign = MaterialTheme.typography.headlineMedium.textAlign,
                )
                CustomText(
                    text = stringResource(Res.string.log_in),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign,
                )
            }
        },
        content = {},
        footer = {}
    )
}

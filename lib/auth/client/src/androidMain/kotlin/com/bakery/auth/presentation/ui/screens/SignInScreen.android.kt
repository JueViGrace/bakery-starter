package com.bakery.auth.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.bakery.auth.presentation.events.AuthEvents
import com.bakery.auth.presentation.state.SignInState
import com.bakery.auth.presentation.ui.components.PasswordTextField
import com.bakery.auth.presentation.ui.components.UsernameTextField
import com.bakery.core.presentation.ui.components.display.ImageComponent
import com.bakery.core.presentation.ui.components.display.TextComponent
import com.bakery.core.presentation.ui.components.layout.mobile.MobileAuthMenu
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.app_logo
import com.bakery.core.resources.resources.generated.resources.app_name
import com.bakery.core.resources.resources.generated.resources.forgot_password
import com.bakery.core.resources.resources.generated.resources.log_in
import com.bakery.core.resources.resources.generated.resources.please_log_in
import com.bakery.core.resources.resources.generated.resources.reimu
import com.bakery.core.resources.resources.generated.resources.welcome_back
import com.bakery.core.shared.types.Constants
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun SignInScreen(
    state: SignInState,
    onEvent: (AuthEvents) -> Unit,
) {
    MobileAuthMenu(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextComponent(
                    text = stringResource(Res.string.welcome_back),
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                    textAlign = MaterialTheme.typography.headlineMedium.textAlign,
                )
                TextComponent(
                    text = stringResource(Res.string.please_log_in),
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    textAlign = MaterialTheme.typography.titleLarge.textAlign,
                )
            }

            ImageComponent(
                modifier = Modifier.size(100.dp),
                painter = painterResource(Res.drawable.reimu),
                contentDescription = stringResource(Res.string.app_logo)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth().weight(0.7f),
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UsernameTextField(
                        value = state.username,
                        onValueChange = { newValue ->
                            onEvent(AuthEvents.OnSignInUsernameChanged(newValue))
                        },
                        errorMessage = state.usernameError?.let { stringResource(it) }
                    )

                    PasswordTextField(
                        value = state.password,
                        onValueChange = { newValue ->
                            onEvent(AuthEvents.OnSignInPasswordChanged(newValue))
                        },
                        errorMessage = state.passwordError?.let { stringResource(it) },
                        passwordVisibility = state.passwordVisibility,
                        onVisibilityChange = {
                            onEvent(AuthEvents.TogglePasswordVisibility)
                        }
                    )
                }

                TextComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.forgot_password),
                    textAlign = TextAlign.Start,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.secondary
                )

                OutlinedButton(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = {
                        onEvent(AuthEvents.OnSignInSubmit)
                    },
                    enabled = state.signInEnabled
                ) {
                    TextComponent(
                        text = stringResource(Res.string.log_in)
                    )
                }
            }
        },
        footer = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextComponent(
                    text = stringResource(Res.string.app_name),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                )
                TextComponent(
                    text = Constants.APP_VERSION,
                    fontSize = MaterialTheme.typography.labelMedium.fontSize,
                    fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                )
            }
        }
    )
}

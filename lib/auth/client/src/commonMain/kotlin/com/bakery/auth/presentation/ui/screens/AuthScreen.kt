package com.bakery.auth.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bakery.auth.presentation.viewmodel.AuthViewModel
import com.bakery.core.presentation.ui.components.display.ImageComponent
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.reimu
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthScreen(
    viewmodel: AuthViewModel = koinViewModel()
) {
    val state by viewmodel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(0.25f),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageComponent(
                modifier = Modifier.size(100.dp).clip(CircleShape).padding(8.dp),
                painter = painterResource(Res.drawable.reimu),
                contentDescription = "Logo"
            )
        }


        AnimatedVisibility(visible = state.isSignIn) {
            SignInScreen(
                state = state.signInState,
                onEvent = viewmodel::onEvent
            )
        }

        AnimatedVisibility(visible = !state.isSignIn) {
            SignUpScreen(
                state = state.signUpState,
                onEvent = viewmodel::onEvent
            )
        }
    }
}

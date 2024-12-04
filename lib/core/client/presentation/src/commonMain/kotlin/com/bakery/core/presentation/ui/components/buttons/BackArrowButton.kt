package com.bakery.core.presentation.ui.components.buttons

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import com.bakery.core.presentation.ui.components.icons.IconComponent
import com.bakery.core.resources.resources.generated.resources.Res
import com.bakery.core.resources.resources.generated.resources.ic_chevron_left
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackArrowButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        IconComponent(
            painter = painterResource(Res.drawable.ic_chevron_left),
            contentDescription = "On back"
        )
    }
}

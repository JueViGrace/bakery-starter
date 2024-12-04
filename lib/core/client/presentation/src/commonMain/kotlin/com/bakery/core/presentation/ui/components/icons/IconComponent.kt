package com.bakery.core.presentation.ui.components.icons

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun IconComponent(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) {
    Icon(
        modifier = modifier.sizeIn(minWidth = 24.dp, minHeight = 24.dp, maxWidth = 26.dp, maxHeight = 26.dp),
        painter = painter,
        contentDescription = contentDescription,
        tint = tint
    )
}

package com.himanshu.sarkari_yojna.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val screenStartSpacing: Dp = 20.dp,
    val screenEndSpacing: Dp = 20.dp
)

val LocalSpacing = compositionLocalOf {
    Spacing()
}

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
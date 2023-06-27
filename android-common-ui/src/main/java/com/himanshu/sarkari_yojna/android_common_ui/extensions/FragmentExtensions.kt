package com.himanshu.sarkari_yojna.android_common_ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.himanshu.sarkari_yojna.theme.SarkariYojnaTheme

fun Fragment.composeView(
    content: @Composable () -> Unit
) = ComposeView(requireContext()).apply {
    // Dispose the Composition when viewLifecycleOwner is destroyed
    setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

    setContent {
        SarkariYojnaTheme(
            content = content
        )
    }
}
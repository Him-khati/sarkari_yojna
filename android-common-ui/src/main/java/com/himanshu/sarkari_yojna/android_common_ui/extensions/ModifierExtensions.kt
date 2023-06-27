package com.himanshu.sarkari_yojna.android_common_ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer

fun Modifier.showShimmer() = composed {
    this.placeholder(
        visible = true,
        color = Color.LightGray,

        // optional, defaults to RectangleShape
        shape = MaterialTheme.shapes.large,
        highlight = PlaceholderHighlight.shimmer(
            highlightColor = Color.White
        )
    )
}

fun Modifier.showShimmer(
    visible: Boolean
) = this.run {
    if (visible) {
        this.showShimmer()
    } else {
        this
    }
}





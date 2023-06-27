package com.himanshu.sarkari_yojna.android_common_ui.extensions

import java.util.*

fun String.capitalizeFirstLetter() = this.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}
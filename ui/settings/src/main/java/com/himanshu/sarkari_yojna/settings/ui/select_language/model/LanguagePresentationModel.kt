package com.himanshu.sarkari_yojna.settings.ui.select_language.model

import androidx.annotation.ColorRes
import com.himanshu.sarkariyojna.android_base.language.Language

data class LanguagePresentationModel(
    val language: Language,
    @ColorRes var color: Int
)

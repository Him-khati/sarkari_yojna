package com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data

import com.himanshu.sarkariyojna.android_base.language.Language

data class SectionMetaData(
    val sectionId: String,
    val title: String,
    val selectedLanguage: Language,
    val languageInAvailable: List<Language>
)
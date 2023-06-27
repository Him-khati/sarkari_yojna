package com.himanshu.sarkariyojna.android_base.language

import androidx.compose.ui.graphics.Color

data class Language(
    val langaugeCode: String,
    val languageSymbol: String,
    val languageName: String,
    val colorForLanguage : Color
) {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Language -> {
                this.langaugeCode == other.langaugeCode
            }
            else -> false
        }
    }

    override fun hashCode(): Int {
        return langaugeCode.hashCode()
    }

    companion object {

        val Hindi = Language(
            langaugeCode = "hi",
            languageName = "Hindi",
            languageSymbol = "आ",
            colorForLanguage = Color(0xFFB95344),
            )

        val English = Language(
            langaugeCode = "en",
            languageName = "English",
            languageSymbol = "A",
            colorForLanguage =Color(0xFF015CBB)
        )

        fun fromLanguageCodeResultOrThrow(
            languageCode: String
        ): Language {

            return when (languageCode) {
                English.langaugeCode -> English
                Hindi.langaugeCode -> Hindi
                else -> throw IllegalArgumentException("no language defined for langauge code : $languageCode")
            }
        }

        fun fromLanguageCodeResultOrNull(
            languageCode: String
        ): Language? {
            return when (languageCode) {
                English.langaugeCode -> English
                Hindi.langaugeCode -> Hindi
                else -> null
            }
        }
    }

    fun isSameAs(
        language: Language
    ): Boolean = this.langaugeCode == language.langaugeCode

}

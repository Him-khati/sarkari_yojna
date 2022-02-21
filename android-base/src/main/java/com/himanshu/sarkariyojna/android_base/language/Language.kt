package com.himanshu.sarkariyojna.android_base.language

data class Language(
    val langaugeCode: String,
    val languageSymbol: String,
    val languageName: String
) {
    companion object {

        val Hindi = Language(
            langaugeCode = "hi",
            languageName = "Hindi",
            languageSymbol = "आ"
        )

        val English = Language(
            langaugeCode = "en",
            languageName = "English",
            languageSymbol = "A"
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

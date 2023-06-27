package com.himanshu.sarkariyojna.android_base.language

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionIndependentDependentDataStore
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagePreferenceManager @Inject constructor(
    @SessionIndependentDependentDataStore private val datStore: DataStore<Preferences>
) {

    companion object {
        private val KEY_SELECTED_LANGUAGE_CODE = stringPreferencesKey("xpRZdw0crQIt")
    }

    fun getSelectedLanguageAsFlow(): Flow<Language> {
        return datStore.data.map {
            val selectedLanguageCode =
                it[KEY_SELECTED_LANGUAGE_CODE] ?: Language.English.langaugeCode
            Language.fromLanguageCodeResultOrNull(selectedLanguageCode) ?: Language.English
        }
    }

    suspend fun getSelectedLanguage(): Language? {
        return getSelectedLanguageAsFlow().firstOrNull()
    }

    suspend fun saveLanguage(
        language: Language
    ) = datStore.edit {
        it[KEY_SELECTED_LANGUAGE_CODE] = language.langaugeCode
    }

    fun getDefaultAppLanguage() : Language{
        return Language.English
    }

}
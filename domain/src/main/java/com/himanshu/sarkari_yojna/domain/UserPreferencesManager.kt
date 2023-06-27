package com.himanshu.sarkari_yojna.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionDependentDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesManager @Inject constructor(
    @SessionDependentDataStore private val datastore: DataStore<Preferences>
) {

    companion object {
        private val KEY_SELECTED_PREFERENCES = stringSetPreferencesKey("9G5pt0KPUh")
    }

    suspend fun updateUserSelectedCategories(
        categories: List<YojnaCategory>
    ) {
        val userSelectedCategories = userSelectedCategories().last()
        val updatedCategoriesList = userSelectedCategories.toMutableSet().apply {

            categories.forEach {
                this.add(it.id)
            }
        }

        datastore.edit {
            it[KEY_SELECTED_PREFERENCES] = updatedCategoriesList
        }
    }

    fun userSelectedCategories(): Flow<Set<String>> = datastore.data.map {
        it[KEY_SELECTED_PREFERENCES] ?: emptySet()
    }
}
package com.himanshu.sarkari_yojna.domain.useCases.categories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionIndependentDependentDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.time.Duration
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetYojnaCategoriesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    @SessionIndependentDependentDataStore private val dataStore: DataStore<Preferences>,
    private val yojnaCategoriesRemoteDataStore: YojnaCategoriesRemoteDataStore,
    private val yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStore
) : UseCase<Unit, Flow<List<YojnaCategory>>>(ioDisPatcher) {

    override suspend fun execute(
        parameters: Unit
    ): Flow<List<YojnaCategory>> {

        return yojnaCategoriesLocalDataStore
            .getCachedYojnaCategories()
            .onStart { updateYojnaCategoriesIfNeeded() }
    }

    private suspend fun updateYojnaCategoriesIfNeeded() {
        if (shouldUpdateYojnaCategories()) {

            val updatedYojnaCategories = yojnaCategoriesRemoteDataStore.getYojnaCategories()
            yojnaCategoriesLocalDataStore.updateYojnaCategories(updatedYojnaCategories)
            updateLastYojnaUpdateTimeStamp()
        }
    }

    private suspend fun updateLastYojnaUpdateTimeStamp() {
        dataStore.edit {
            it[LAST_CATEGORIES_UPDATED_AT] = LocalDate.now().toString()
        }
    }

    private suspend fun shouldUpdateYojnaCategories() = dataStore.data.map {
        val lastUpdateTimeStamp = it[LAST_CATEGORIES_UPDATED_AT]
        if (lastUpdateTimeStamp == null) true
        else {
            val lastUpdateTime = LocalDate.parse(lastUpdateTimeStamp)
            Duration.between(lastUpdateTime,LocalDate.now()).toDays() > UPDATE_CATEGORIES_IN_DAYS
        }
    }.last()


    companion object {
        private val LAST_CATEGORIES_UPDATED_AT = stringPreferencesKey("8q92b4O10Z")
        private const val UPDATE_CATEGORIES_IN_DAYS = 7L
    }
}
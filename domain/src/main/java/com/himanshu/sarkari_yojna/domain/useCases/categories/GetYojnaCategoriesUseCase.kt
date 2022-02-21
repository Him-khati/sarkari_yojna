package com.himanshu.sarkari_yojna.domain.useCases.categories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.himanshu.sarkari_yojna.domain.UserPreferencesManager
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionDependentDataStore
import com.himanshu.sarkariyojna.core.logger.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import java.time.Duration
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetYojnaCategoriesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    @SessionDependentDataStore private val dataStore: DataStore<Preferences>,
    private val yojnaCategoriesRemoteDataStore: YojnaCategoriesRemoteDataStore,
    private val yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStore,
    private val userPreferencesManager: UserPreferencesManager,
    private val logger: Logger
) : UseCase<Unit?, Flow<List<YojnaCategoryPresentationModel>>>(ioDisPatcher) {

    companion object{
        private const val TAG = "GetYojnaCategoriesUseCase"
        private val LAST_CATEGORIES_UPDATED_AT = stringPreferencesKey("8q92b4O10Z")
        private const val UPDATE_CATEGORIES_IN_DAYS = 7L
    }

    override suspend fun execute(
        parameters: Unit?
    ): Flow<List<YojnaCategoryPresentationModel>> {

      return userPreferencesManager.userSelectedCategories()
            .onStart { updateYojnaCategoriesIfNeeded() }
            .combine(yojnaCategoriesLocalDataStore.getCachedYojnaCategories()){
                    userSelectedYojnaIds: Set<String>,
                    yojnaCategories: List<YojnaCategory> ->

                    yojnaCategories.map {
                        YojnaCategoryPresentationModel(
                            category =  it,
                            selected = userSelectedYojnaIds.contains(it.id)
                        )
                    }
            }
    }

    private suspend fun updateYojnaCategoriesIfNeeded() {
        logger.v(TAG,"Checking if need to update yojna categories....")

        if (shouldUpdateYojnaCategories()) {
            logger.v(TAG,"updating categories....")

            val updatedYojnaCategories = yojnaCategoriesRemoteDataStore.getYojnaCategories()
            yojnaCategoriesLocalDataStore.updateYojnaCategories(updatedYojnaCategories)
            updateLastYojnaUpdateTimeStamp()

            logger.d(TAG,"[Success] categories updated")
        }
    }

    private suspend fun updateLastYojnaUpdateTimeStamp() {
        dataStore.edit {
            it[LAST_CATEGORIES_UPDATED_AT] = LocalDate.now().toString()
        }
    }

    private suspend fun shouldUpdateYojnaCategories() = dataStore.data.map {
        val lastUpdateTimeStamp = it[LAST_CATEGORIES_UPDATED_AT]
        logger.d(
            TAG,
            "last categories update timestamp : $lastUpdateTimeStamp"
        )

        if (lastUpdateTimeStamp == null) true
        else {
            val lastUpdateTime = LocalDate.parse(lastUpdateTimeStamp)
            Duration.between(lastUpdateTime,LocalDate.now()).toDays() > UPDATE_CATEGORIES_IN_DAYS
        }
    }.last()

}
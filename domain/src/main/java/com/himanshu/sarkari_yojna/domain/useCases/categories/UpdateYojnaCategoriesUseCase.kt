package com.himanshu.sarkari_yojna.domain.useCases.categories

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.himanshu.sarkari_yojna.domain.UserPreferencesManager
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.domain.useCases.FlowUseCase
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionDependentDataStore
import com.himanshu.sarkariyojna.core.logger.Logger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import java.time.Duration
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateYojnaCategoriesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    @SessionDependentDataStore private val dataStore: DataStore<Preferences>,
    private val yojnaCategoriesRemoteDataStore: YojnaCategoriesRemoteDataStore,
    private val yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStore,
    private val logger: Logger
) : FlowUseCase<Unit?, UpdateYojnaCategoriesUseCase.State>(ioDisPatcher) {

    companion object {
        private const val TAG = "GetYojnaCategoriesUseCase"
        private val LAST_CATEGORIES_UPDATED_AT = stringPreferencesKey("8q92b4O10Z")
        private const val UPDATE_CATEGORIES_IN_DAYS = 7L
        private val THIRTY_SECONDS = Duration.ofSeconds(30L).toMillis()
    }

    override fun execute(parameters: Unit?): Flow<State> = flow {


        logger.v(TAG, "Checking if need to update yojna categories....")
        val shouldUpdateYojnaCategories = shouldUpdateYojnaCategories()
        logger.v(TAG, "shouldUpdateYojnaCategories() : $shouldUpdateYojnaCategories")

        if (!shouldUpdateYojnaCategories) {
            emit(State.CategoriesUpdated)
        } else {
            emit(State.CategoriesUpdating)

            try {
                withTimeout(THIRTY_SECONDS) {
                    updateYojnaCategories()
                    emit(State.CategoriesUpdated)
                }
            } catch (e: Exception) {
                emit(
                    State.ErrorWhileUpdatingCategories(
                        e.message ?: "Unable to update Yojna Categories"
                    )
                )
            }
        }
    }

    private suspend fun updateYojnaCategories() {

        val updatedYojnaCategories = yojnaCategoriesRemoteDataStore.getYojnaCategories()
        yojnaCategoriesLocalDataStore.updateYojnaCategories(updatedYojnaCategories)
        updateLastYojnaUpdateTimeStamp()

        logger.d(TAG, "[Success] categories updated")
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
            Duration.between(
                lastUpdateTime.atStartOfDay(),
                LocalDate.now().atStartOfDay()
            ).toDays() > UPDATE_CATEGORIES_IN_DAYS
        }
    }.first()


    sealed class State {

        object CategoriesUpdating : State()

        object CategoriesUpdated : State()

        data class ErrorWhileUpdatingCategories(
            val error: String
        ) : State()
    }
}
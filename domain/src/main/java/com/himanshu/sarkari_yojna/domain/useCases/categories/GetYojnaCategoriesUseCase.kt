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
import com.himanshu.sarkari_yojna.domain.useCases.FlowUseCase
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
    private val yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStore,
    private val userPreferencesManager: UserPreferencesManager,
    private val logger: Logger
) : FlowUseCase<Unit?, List<YojnaCategoryPresentationModel>>(ioDisPatcher) {

    override fun execute(
        parameters: Unit?
    ): Flow<List<YojnaCategoryPresentationModel>> {
        return getCachedYojnaCategoriesFlow()
    }

    private fun getCachedYojnaCategoriesFlow(): Flow<List<YojnaCategoryPresentationModel>> {

        return yojnaCategoriesLocalDataStore
            .getCachedYojnaCategories()
            .map { mapTOYojnaCategoriesPresentationModel(it) }
            .combine(userPreferencesManager.userSelectedCategories()) { yojnaCategories: List<YojnaCategoryPresentationModel>,
                                                                        userSelectedYojnaIds: Set<String> ->

                yojnaCategories.onEach {
                    it.selected = userSelectedYojnaIds.contains(it.id)
                }
            }
    }

    private fun mapTOYojnaCategoriesPresentationModel(
        yojnaCategories: List<YojnaCategory>
    ) = yojnaCategories.map {
        YojnaCategoryPresentationModel(
            id = it.id,
            category = it,
            selected = false
        )
    }


}
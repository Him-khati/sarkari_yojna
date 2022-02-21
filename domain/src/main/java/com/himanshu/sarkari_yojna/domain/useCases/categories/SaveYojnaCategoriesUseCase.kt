package com.himanshu.sarkari_yojna.domain.useCases.categories

import com.himanshu.sarkari_yojna.domain.UserPreferencesManager
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import com.himanshu.sarkariyojna.core.logger.Logger
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveYojnaCategoriesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    private val userPreferencesManager: UserPreferencesManager,
    private val logger: Logger
) : UseCase<List<YojnaCategory>, Unit>(ioDisPatcher) {

    companion object {
        private const val TAG = "SaveYojnaCategoriesUseCase"
    }

    override suspend fun execute(parameters: List<YojnaCategory>) {
        userPreferencesManager.updateUserSelectedCategories(parameters)
    }
}
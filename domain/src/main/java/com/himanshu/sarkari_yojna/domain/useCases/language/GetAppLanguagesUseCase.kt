package com.himanshu.sarkari_yojna.domain.useCases.language

import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppLanguagesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher
) : UseCase<Unit?, List<Language>>(ioDisPatcher) {

    override suspend fun execute(parameters: Unit?): List<Language> {
        return listOf(
            Language.English,
            Language.Hindi
        )
    }
}
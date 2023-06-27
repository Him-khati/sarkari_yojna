package com.himanshu.sarkari_yojna.domain.useCases.language

import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.android_base.language.LanguagePreferenceManager
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserSelectedLanguageUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    private val languagePreferenceManager: LanguagePreferenceManager
) : UseCase<Unit?, Language?>(ioDisPatcher) {

    override suspend fun execute(
        parameters: Unit?
    ): Language? {
       return languagePreferenceManager.getSelectedLanguage()
    }
}
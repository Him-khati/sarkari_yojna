package com.himanshu.sarkari_yojna.domain.useCases.yojna_details

import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.repositories.YojnaRepository
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetYojnaDetailsUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    private val yojnaRepository: YojnaRepository
) : UseCase<GetYojnaDetailsUseCase.Parameters, YojnaDetails>(ioDisPatcher) {

    override suspend fun execute(parameters: Parameters): YojnaDetails {
        return yojnaRepository.getYojnaDetails(
            yojnaId = parameters.yojnaId,
            requiredLanguage = parameters.language
        )
    }

    data class Parameters(
        val yojnaId: String,
        val language: Language
    )
}
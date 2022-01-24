package com.himanshu.sarkari_yojna.domain.useCases.yojna_details

import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkari_yojna.domain.repositories.YojnaRepository
import com.himanshu.sarkari_yojna.domain.useCases.UseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class GetYojnaMetaDataUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
    private val yojnaRepository: YojnaRepository
) : UseCase<GetYojnaMetaDataUseCase.Parameters, YojnaMetaData>(ioDisPatcher) {

    override suspend fun execute(parameters: Parameters): YojnaMetaData {
        return yojnaRepository.getYojnaMetaData(
            yojnaId = parameters.yojnaId
        )
    }

    data class Parameters(
        val yojnaId: String
    )
}
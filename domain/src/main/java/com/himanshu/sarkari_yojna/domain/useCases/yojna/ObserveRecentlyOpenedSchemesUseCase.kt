package com.himanshu.sarkari_yojna.domain.useCases.yojna

import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaOverview
import com.himanshu.sarkari_yojna.domain.useCases.FlowUseCase
import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ObserveRecentlyOpenedSchemesUseCase @Inject constructor(
    @IoDispatcher ioDisPatcher: CoroutineDispatcher,
) : FlowUseCase<ObserveRecentlyOpenedSchemesUseCase.Parameters, List<YojnaOverview>>(
    ioDisPatcher
) {

    override fun execute(parameters: Parameters): Flow<List<YojnaOverview>> {
        TODO("Not yet implemented")
    }

    data class Parameters(
        val count: Int
    )
}
package com.himanshu.sarkari_yojna.domain.useCases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Result.Error] to the result) is the subclasses's responsibility.
 */
abstract class FlowUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    operator fun invoke(parameters: P): Flow<R> = execute(parameters)
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<R>
}

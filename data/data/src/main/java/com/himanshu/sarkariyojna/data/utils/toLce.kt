package com.himanshu.sarkariyojna.data.utils

import com.dropbox.android.external.store4.StoreResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

fun <T> Flow<StoreResponse<T>>.changeDispatcherAndConvertToLce(): Flow<Lce<T>> {

    return this
        .flowOn(Dispatchers.IO)
        .map { response ->
        when (response) {
            is StoreResponse.Loading -> {
                Lce.loading()
            }
            is StoreResponse.Error -> {
                Lce.error(
                    response.errorMessageOrNull() ?: "Unable to update data"
                )
            }
            is StoreResponse.Data -> {
                Lce.content(response.value)
            }
            is StoreResponse.NoNewData -> Lce.content(null)
        } as Lce<T>
    }
}
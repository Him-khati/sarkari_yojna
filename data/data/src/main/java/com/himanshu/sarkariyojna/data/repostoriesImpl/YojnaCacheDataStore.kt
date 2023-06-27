package com.himanshu.sarkariyojna.data.repostoriesImpl

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow

interface YojnaCacheDataStore {

    fun getYojnaList(
        transformation : YojnaListTransformation
    ) : Flow<List<Yojna>>

    suspend fun addOrUpdateYojna(
        yojnas : List<Yojna>
    )

    suspend fun deleteYojna(
        fetchConfigId : String
    )

    suspend fun deleteAllCachedYojnas()
}
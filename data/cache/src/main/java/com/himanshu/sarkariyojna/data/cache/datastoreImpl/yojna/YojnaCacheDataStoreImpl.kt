package com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaCacheDataStore
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaCacheDataStoreImpl @Inject constructor() : YojnaCacheDataStore {

    override fun getYojnaList(transformation: YojnaListTransformation): Flow<List<Yojna>> {
        TODO("Not yet implemented")
    }

    override suspend fun addOrUpdateYojna(yojnas: List<Yojna>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteYojna(fetchConfigId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllCachedYojnas() {
        TODO("Not yet implemented")
    }

}
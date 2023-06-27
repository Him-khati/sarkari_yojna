package com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.cache.dao.YojnaDao
import com.himanshu.sarkariyojna.data.cache.entity_mappers.YojnaEntityMapper
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaCacheDataStore
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class YojnaLocalDataStoreImpl @Inject constructor(
    private val yojnaDao: YojnaDao
) : YojnaCacheDataStore {


    override fun getYojnaList(
        transformation: YojnaListTransformation
    ): Flow<List<Yojna>> {
        val yojnaList = yojnaDao.getYojna().map { cachedYojnaList ->
            cachedYojnaList.map {
                YojnaEntityMapper.fromCachedSarkariYojna(it)
            }
        }

        return transformation.transformYojnaList(
            yojnaList
        )
    }

    override suspend fun addOrUpdateYojna(yojnas: List<Yojna>) {
        val mappedYojnaList = yojnas.map {
            YojnaEntityMapper.toCachedSarkariYojna(it)
        }
        yojnaDao.insertOrUpdateYojna(
            mappedYojnaList
        )
    }

    override suspend fun deleteYojna(
        fetchConfigId: String
    ) {
    }

    override suspend fun deleteAllCachedYojnas() {
        yojnaDao.deleteAllYojnas()
    }
}
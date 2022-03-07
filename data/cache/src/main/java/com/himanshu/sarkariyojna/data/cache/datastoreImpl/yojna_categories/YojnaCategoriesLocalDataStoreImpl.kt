package com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories

import android.util.Log
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkariyojna.data.cache.dao.SarkariYojnaCategoryDao
import com.himanshu.sarkariyojna.data.cache.entity_mappers.SarkariYojnaCategoriesEntityMapper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class YojnaCategoriesLocalDataStoreImpl @Inject constructor(
    private val sarkariYojnaCategoryDao: SarkariYojnaCategoryDao
) : YojnaCategoriesLocalDataStore {

    override suspend fun updateYojnaCategories(
        yojnaCategories: List<YojnaCategory>
    ) {

        sarkariYojnaCategoryDao.insert(
            yojnaCategories.map { SarkariYojnaCategoriesEntityMapper.toCachedSarkariYojnaCategory(it) }
        )
    }

    override  fun getCachedYojnaCategories(): Flow<List<YojnaCategory>> {

        return sarkariYojnaCategoryDao.getYojnaCategories()
            .map {
                it.map { SarkariYojnaCategoriesEntityMapper.fromCachedSarkariYojnaCategory(it) }
            }
    }
}
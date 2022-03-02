package com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YojnaCategoriesLocalDataStoreImpl @Inject constructor() : YojnaCategoriesLocalDataStore {

    override fun updateYojnaCategories(
        yojnaCategories: List<YojnaCategory>
    ) {
        TODO("Not yet implemented")
    }

    override fun getCachedYojnaCategories(): Flow<List<YojnaCategory>> {
        TODO("Not yet implemented")
    }
}
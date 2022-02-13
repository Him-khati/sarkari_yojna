package com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna_categories

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaCategoriesRemoteDataStoreImpl @Inject constructor() : YojnaCategoriesRemoteDataStore {

    override suspend fun getYojnaCategories(): List<YojnaCategory> {
        TODO("Not yet implemented")
    }
}
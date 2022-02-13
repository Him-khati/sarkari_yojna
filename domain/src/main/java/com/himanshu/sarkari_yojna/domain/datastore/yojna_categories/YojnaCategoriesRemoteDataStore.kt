package com.himanshu.sarkari_yojna.domain.datastore.yojna_categories

import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory

interface YojnaCategoriesRemoteDataStore {

    suspend fun getYojnaCategories() : List<YojnaCategory>
}
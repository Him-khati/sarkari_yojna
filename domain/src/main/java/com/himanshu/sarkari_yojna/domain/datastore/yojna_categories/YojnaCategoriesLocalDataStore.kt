package com.himanshu.sarkari_yojna.domain.datastore.yojna_categories

import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import kotlinx.coroutines.flow.Flow

interface YojnaCategoriesLocalDataStore {

    suspend fun updateYojnaCategories(
        yojnaCategories : List<YojnaCategory>
    )

    fun getCachedYojnaCategories() : Flow<List<YojnaCategory>>
}
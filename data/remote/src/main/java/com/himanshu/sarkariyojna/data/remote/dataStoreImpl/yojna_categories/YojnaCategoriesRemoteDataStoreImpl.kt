package com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna_categories

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaCategoriesRemoteDataStoreImpl @Inject constructor() : YojnaCategoriesRemoteDataStore {

    override suspend fun getYojnaCategories(): List<YojnaCategory> {
       return listOf(
           YojnaCategory(
               id = "id_1", name = "Cat-1", description = "Hello", updatedOn = LocalDateTime.now()
           ),
           YojnaCategory(
               id = "id_2", name = "Cat-2", description = "Hello", updatedOn = LocalDateTime.now()
           ),
           YojnaCategory(
               id = "id_3", name = "Cat-3", description = "Hello", updatedOn = LocalDateTime.now()
           ),
           YojnaCategory(
               id = "id_4", name = "Cat-4", description = "Hello", updatedOn = LocalDateTime.now()
           )
       )

    }
}
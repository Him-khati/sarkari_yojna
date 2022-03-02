package com.himanshu.sarkariyojna.di.modules

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories.YojnaCategoriesLocalDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindYojnaCategoriesLocalDataStore(
        yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStoreImpl
    ) : YojnaCategoriesLocalDataStore

}
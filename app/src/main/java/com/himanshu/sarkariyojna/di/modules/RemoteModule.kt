package com.himanshu.sarkariyojna.di.modules

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna.YojnaRemoteDataStoreImpl
import com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna_categories.YojnaCategoriesRemoteDataStoreImpl
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaRemoteDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    @Binds
    abstract fun bindYojnaCategoriesRemoteDataStore(
        yojnaCategoriesLocalDataStore: YojnaCategoriesRemoteDataStoreImpl
    ) : YojnaCategoriesRemoteDataStore

    @Binds
    abstract fun bindYojnaRemoteDataStore(
        yojnaCategoriesLocalDataStore: YojnaRemoteDataStoreImpl
    ) : YojnaRemoteDataStore
}
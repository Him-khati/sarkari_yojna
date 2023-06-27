package com.himanshu.sarkariyojna.di.modules

import android.content.Context
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkariyojna.data.cache.LocalDatabase
import com.himanshu.sarkariyojna.data.cache.dao.SarkariYojnaCategoryDao
import com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna.YojnaCacheDataStoreImpl
import com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories.YojnaCategoriesLocalDataStoreImpl
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaCacheDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {


    companion object {

        @Singleton
        @Provides
        fun provideLocalDatabase(
           @ApplicationContext applicationContext: Context
        ): LocalDatabase {
           return LocalDatabase.createDatabase(applicationContext)
        }

        @Singleton
        @Provides
        fun provideYojnaCategoryDao(
            localDatabase: LocalDatabase
        ) : SarkariYojnaCategoryDao {
            return localDatabase.getSarkariYojnaCategoriesDao()
        }
    }

    @Binds
    @Singleton
    abstract fun bindYojnaCategoriesLocalDataStore(
        yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStoreImpl
    ): YojnaCategoriesLocalDataStore

    @Binds
    @Singleton
    abstract fun bindYojnaCacheDataStore(
        YojnaCacheDataStore: YojnaCacheDataStoreImpl
    ): YojnaCacheDataStore




}
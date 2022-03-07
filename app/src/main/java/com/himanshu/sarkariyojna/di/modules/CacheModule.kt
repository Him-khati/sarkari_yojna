package com.himanshu.sarkariyojna.di.modules

import android.content.Context
import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesLocalDataStore
import com.himanshu.sarkariyojna.data.cache.LocalDatabase
import com.himanshu.sarkariyojna.data.cache.dao.SarkariYojnaCategoryDao
import com.himanshu.sarkariyojna.data.cache.datastoreImpl.yojna_categories.YojnaCategoriesLocalDataStoreImpl
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
    abstract fun bindYojnaCategoriesLocalDataStore(
        yojnaCategoriesLocalDataStore: YojnaCategoriesLocalDataStoreImpl
    ): YojnaCategoriesLocalDataStore

}
package com.himanshu.sarkariyojna.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionDependentDataStore
import com.himanshu.sarkariyojna.core.di.quatifiers.dataStore.SessionIndependentDependentDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AndroidUtilModule {

    companion object {
        private const val SESSION_DEPENDENT_STORE_FILE_NAME = "GEmlP05otsYc"
        private const val SESSION_INDEPENDENT_STORE_FILE_NAME = "G08YXPK5zYdg"

        @SessionDependentDataStore
        @Provides
        @JvmStatic
        fun provideSessionDependentDataStore(
            @ApplicationContext appContext: Context
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                produceFile = {
                    appContext.preferencesDataStoreFile(SESSION_DEPENDENT_STORE_FILE_NAME)
                }
            )
        }

        @SessionIndependentDependentDataStore
        @Provides
        @JvmStatic
        fun provideSessionInDependentDataStore(
            @ApplicationContext appContext: Context
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                produceFile = {
                    appContext.preferencesDataStoreFile(SESSION_INDEPENDENT_STORE_FILE_NAME)
                }
            )
        }
    }


}
package com.himanshu.sarkariyojna.di.modules

import com.himanshu.sarkariyojna.core.di.quatifiers.IoDispatcher
import com.himanshu.sarkariyojna.core.di.quatifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {


    @MainDispatcher
    @Provides
    fun provideMainCoroutinesDispatcher() : CoroutineDispatcher{
        return Dispatchers.Main
    }

    @IoDispatcher
    @Provides
    fun provideIoCoroutinesDispatcher() : CoroutineDispatcher{
        return Dispatchers.IO
    }





}
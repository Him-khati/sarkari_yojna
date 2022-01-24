package com.himanshu.sarkariyojna.di.modules

import com.himanshu.sarkari_yojna.domain.repositories.YojnaRepository
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindYojnaRepository(yojnaRepositoryImpl: YojnaRepositoryImpl) : YojnaRepository
}
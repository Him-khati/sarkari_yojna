package com.himanshu.sarkariyojna.di.modules

import com.himanshu.sarkari_yojna.domain.datastore.yojna_categories.YojnaCategoriesRemoteDataStore
import com.himanshu.sarkari_yojna.navigation.SarkariYojnaNavigation
import com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna_categories.YojnaCategoriesRemoteDataStoreImpl
import com.himanshu.sarkariyojna.navImpl.SarkariYojnaNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindSarkariYojnaNavigation(
        navigationImpl: SarkariYojnaNavigationImpl
    ) : SarkariYojnaNavigation
}
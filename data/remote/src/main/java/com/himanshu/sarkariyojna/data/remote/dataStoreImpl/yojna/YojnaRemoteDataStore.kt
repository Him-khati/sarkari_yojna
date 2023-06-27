package com.himanshu.sarkariyojna.data.remote.dataStoreImpl.yojna

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaRemoteDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaRemoteDataStoreImpl @Inject constructor(): YojnaRemoteDataStore {

    override fun getYojnaList(): List<Yojna> {
        TODO("Not yet implemented")
    }
}
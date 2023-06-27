package com.himanshu.sarkariyojna.data.repostoriesImpl

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna

interface YojnaRemoteDataStore {

    fun getYojnaList() : List<Yojna>
}
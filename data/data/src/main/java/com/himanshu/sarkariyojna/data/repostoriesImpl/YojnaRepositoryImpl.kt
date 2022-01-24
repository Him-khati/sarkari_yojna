package com.himanshu.sarkariyojna.data.repostoriesImpl

import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkari_yojna.domain.repositories.YojnaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class YojnaRepositoryImpl @Inject constructor() : YojnaRepository {

    override suspend fun getYojnaDetails(
        yojnaId: String,
        requiredLanguage: Language
    ): YojnaDetails {
        TODO("Not yet implemented")
    }

    override suspend fun getYojnaMetaData(
        yojnaId: String
    ): YojnaMetaData {
        TODO("Not yet implemented")
    }
}
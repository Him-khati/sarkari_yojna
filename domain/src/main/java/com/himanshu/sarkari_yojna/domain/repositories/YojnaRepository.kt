package com.himanshu.sarkari_yojna.domain.repositories

import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkariyojna.android_base.language.Language

interface YojnaRepository {

    suspend fun getYojnaDetails(
        yojnaId : String,
        requiredLanguage: Language
    ) : YojnaDetails

    suspend fun getYojnaMetaData(
        yojnaId : String
    ) : YojnaMetaData
}
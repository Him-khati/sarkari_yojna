package com.himanshu.sarkariyojna.ui.yojna_details.models

import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails

data class YojnaDetailsPresentationModel(
    val yojnaDetails : YojnaDetails,
    val languageAvailable : List<Language>,
    val selectedLanguage : Language
)

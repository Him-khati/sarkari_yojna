package com.himanshu.sarkari_yojna.domain.models.yojna

import android.net.Uri

data class YojnaDetails(
    val yojnaId : String,
    val category : String,
    val title : String,
    val coverImage : Uri,
    val author : String,
    val content : String,
    val bookmarked : Boolean
)

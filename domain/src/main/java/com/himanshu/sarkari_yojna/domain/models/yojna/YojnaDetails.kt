package com.himanshu.sarkari_yojna.domain.models.yojna

data class YojnaDetails(
    val yojnaId : String,
    val category : String,
    val title : String,
    val coverImage : String,
    val author : String,
    val content : String,
    val bookmarked : Boolean
)

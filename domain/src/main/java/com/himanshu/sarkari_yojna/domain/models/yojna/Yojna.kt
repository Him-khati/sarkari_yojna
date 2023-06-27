package com.himanshu.sarkari_yojna.domain.models.yojna

import java.time.LocalDateTime

data class Yojna(
    val id : String,
    val name: String,
    val artUrl : String?,
    val bookmarked : Boolean,
    val lastOpenedOn : LocalDateTime? = null,
    val updatedOn : LocalDateTime
)
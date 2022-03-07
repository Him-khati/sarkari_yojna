package com.himanshu.sarkari_yojna.domain.models.yojna_category

data class YojnaCategoryPresentationModel(
    val id : String,
    val category : YojnaCategory,
    var selected : Boolean
)

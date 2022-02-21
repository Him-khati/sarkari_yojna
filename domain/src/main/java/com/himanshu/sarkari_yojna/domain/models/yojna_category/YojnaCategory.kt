package com.himanshu.sarkari_yojna.domain.models.yojna_category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class YojnaCategory(
    val id : String,
    val name : String,
    val description : String
) : Parcelable

package com.himanshu.sarkariyojna.data.cache.entity_mappers

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojna
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojnaCategory

object YojnaEntityMapper {

    fun toCachedSarkariYojna(
        yojna : Yojna
    ) : CachedYojna = CachedYojna(
        id = yojna.id,
        name = yojna.name,
        bookmarked = yojna.bookmarked,
        lastOpenedOn = yojna.lastOpenedOn,
        updatedOn = yojna.updatedOn
        )

    fun fromCachedSarkariYojna(
        yojna : CachedYojna
    ) : Yojna = Yojna(
        id = yojna.id,
        name = yojna.name,
        bookmarked = yojna.bookmarked,
        lastOpenedOn = yojna.lastOpenedOn,
        updatedOn = yojna.updatedOn,
        artUrl = null
    )
}
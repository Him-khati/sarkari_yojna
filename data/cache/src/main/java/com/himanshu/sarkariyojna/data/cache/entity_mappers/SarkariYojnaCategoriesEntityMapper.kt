package com.himanshu.sarkariyojna.data.cache.entity_mappers

import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkariyojna.data.cache.entities.CachedYojnaCategory

object SarkariYojnaCategoriesEntityMapper {

    fun toCachedSarkariYojnaCategory(
        category : YojnaCategory
    ) : CachedYojnaCategory = CachedYojnaCategory(
        id = category.id,
        name = category.name,
        description = category.description,
        updatedOn = category.updatedOn
    )

    fun fromCachedSarkariYojnaCategory(
        category : CachedYojnaCategory
    ) : YojnaCategory = YojnaCategory(
        id = category.id,
        name = category.name,
        description = category.description,
        updatedOn = category.updatedOn
    )
}
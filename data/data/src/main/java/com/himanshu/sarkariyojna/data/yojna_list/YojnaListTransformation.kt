package com.himanshu.sarkariyojna.data.yojna_list

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.yojna_list.transformations.FetchBookmarkedYojnas
import com.himanshu.sarkariyojna.data.yojna_list.transformations.FetchRecentlyOpenedYojnas
import kotlinx.coroutines.flow.Flow

abstract class YojnaListTransformation(
    val id: String,
    val name: String
) {

    companion object{
        fun getTransformationById(
            transformationId : String
        ) : YojnaListTransformation = when(transformationId){
            FetchBookmarkedYojnas.getTransformationId() -> FetchBookmarkedYojnas
            FetchRecentlyOpenedYojnas.getTransformationId() -> FetchRecentlyOpenedYojnas
            else -> error("transformation id did not matched with existing transformation list")
        }
    }

    fun getTransformationId(): String {
        return id
    }

    abstract fun transformYojnaList(
        yojnaList : Flow<List<Yojna>>
    ) : Flow<List<Yojna>>
}
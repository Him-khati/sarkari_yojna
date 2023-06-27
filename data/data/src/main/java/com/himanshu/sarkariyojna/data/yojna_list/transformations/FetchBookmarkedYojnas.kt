package com.himanshu.sarkariyojna.data.yojna_list.transformations

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

object FetchBookmarkedYojnas : YojnaListTransformation(
    id = "bookmarked_yojnas",
    name = "Bookmarked Yojnas"
) {


    override fun transformYojnaList(
        yojnaList: Flow<List<Yojna>>
    ): Flow<List<Yojna>> = yojnaList.onEach { yojanList ->
        yojanList.filter {
            it.bookmarked
        }
    }
}
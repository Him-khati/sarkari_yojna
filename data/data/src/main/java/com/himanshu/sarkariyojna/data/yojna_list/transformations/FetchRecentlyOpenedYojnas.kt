package com.himanshu.sarkariyojna.data.yojna_list.transformations

import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.data.yojna_list.YojnaListTransformation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

object FetchRecentlyOpenedYojnas : YojnaListTransformation(
    id = "recently_opened",
    name = "Recently Opened"
) {

    override fun transformYojnaList(yojnaList: Flow<List<Yojna>>): Flow<List<Yojna>> {

        return yojnaList.onEach { yojnaList ->
            yojnaList.sortedBy {
                it.lastOpenedOn
            }
        }
    }
}
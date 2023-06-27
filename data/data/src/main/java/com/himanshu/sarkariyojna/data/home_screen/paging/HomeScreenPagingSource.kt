package com.himanshu.sarkariyojna.data.home_screen.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.himanshu.sarkariyojna.data.home_screen.engine.HomeScreenDataEngine
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection

class HomeScreenPagingSource constructor(
    private val homeScreenDataEngine: HomeScreenDataEngine
) : PagingSource<Int, HomeScreenSection>() {

    override fun getRefreshKey(
        state: PagingState<Int, HomeScreenSection>
    ): Int? = state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, HomeScreenSection> {
        return try {
            val page = params.key ?: 1
            val response = homeScreenDataEngine.getItemsForHomeScreen(
                page = page
            )

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
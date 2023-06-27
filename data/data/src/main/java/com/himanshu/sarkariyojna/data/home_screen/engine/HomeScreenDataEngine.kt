package com.himanshu.sarkariyojna.data.home_screen.engine

import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection
import javax.inject.Inject

class HomeScreenDataEngine @Inject constructor(){

    fun getItemsForHomeScreen(
        page : Int
    ) : List<HomeScreenSection>{
        return emptyList()
    }
}
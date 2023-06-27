package com.himanshu.home_screen.ui

import com.himanshu.sarkari_yojna.NavigationDestination
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkariyojna.android_base.base.UiEffect
import com.himanshu.sarkariyojna.android_base.base.UiEvent
import com.himanshu.sarkariyojna.android_base.base.UiState
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection

sealed class HomeScreenViewState : UiState {

    data class LoadingInitialData(
        val message : String
    ) : HomeScreenViewState()

    data class ShowSectionOnView(
        val refreshingData : Boolean,
        val sectionData : List<HomeScreenSection>
    ) : HomeScreenViewState()


    data class ErrorWhileLoadingInitialData(
        val error : String,
        val showRetryButton : Boolean
    ) : HomeScreenViewState()
}


sealed class HomeScreenViewEvent : UiEvent {

    object RefreshHomeScreen : HomeScreenViewEvent()

    data class CategoryClicked(
        val category : YojnaCategory
    ) : HomeScreenViewEvent()

    data class YojnaClicked(
        val yojna :Yojna
    ) : HomeScreenViewEvent()

}

sealed class HomeScreenViewEffects : UiEffect {

    data class showSnackBar(
        val text : String,
        val action : String?
    ) : HomeScreenViewEffects()


    data class NavigateTo(
        val navigationEvent : NavigationDestination
    ) : HomeScreenViewEffects()
}

package com.himanshu.home_screen.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshu.home_screen.ui.HomeScreenViewEvent
import com.himanshu.home_screen.ui.HomeScreenViewModel
import com.himanshu.home_screen.ui.views.sections.HorizontalYojnaContainer
import com.himanshu.home_screen.ui.views.sections.UpdatePreferenceDataSection
import com.himanshu.sarkari_yojna.NavigationDestination
import com.himanshu.sarkari_yojna.destinations.NavDestinationHomeScreen
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection

@Composable
fun HomeScreenSection(
    sectionData: HomeScreenSection,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    when (sectionData) {
        is HomeScreenSection.HorizontalYojnaSection -> HorizontalYojnaContainer(
            modifier = Modifier,
            sectionId = sectionData.sectionMetaData.sectionId,
            sectionTitle = sectionData.sectionMetaData.title,
            yojna = sectionData.yojnaList,
            showViewMore = sectionData.showViewMore,
            onViewMoreClicked = {
            },
            onYojnaClicked = {
                viewModel.setEvent(HomeScreenViewEvent.YojnaClicked(it))
            }
        )
        is HomeScreenSection.UpdatePreferenceRequest -> UpdatePreferenceDataSection(
            id = "",
            title = sectionData.title,
            subtitle = sectionData.subTitle,
            navDeepLink = NavDestinationHomeScreen,
            onAction = { s: String, navigationDestination: NavigationDestination -> },

            )
        is HomeScreenSection.VerticalYojnaSection -> TODO()
    }
}
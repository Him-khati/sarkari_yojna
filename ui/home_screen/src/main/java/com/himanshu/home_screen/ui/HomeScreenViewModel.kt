package com.himanshu.home_screen.ui

import androidx.lifecycle.viewModelScope
import com.himanshu.sarkari_yojna.destinations.NavDestinationYojnaDetailsScreen
import com.himanshu.sarkari_yojna.domain.models.yojna.Yojna
import com.himanshu.sarkariyojna.android_base.base.BaseViewModel
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.data.home_screen.HomeScreenRepository
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection
import com.himanshu.sarkariyojna.data.home_screen.models.home_screen_items.section_data.SectionMetaData
import com.himanshu.sarkariyojna.data.repostoriesImpl.YojnaRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val yojnaRepository: YojnaRepositoryImpl,
    private val homeScreenDataRepository: HomeScreenRepository
) : BaseViewModel<
        HomeScreenViewEvent,
        HomeScreenViewState,
        HomeScreenViewEffects>(
    initialState = HomeScreenViewState.LoadingInitialData(
        "Loading data"
    )
) {

    init {
        startObservingHomeScreenData()
    }

    private fun startObservingHomeScreenData() = viewModelScope.launch {
        delay(2000L)

        setState {
            HomeScreenViewState.ShowSectionOnView(
                false,
                sectionData = listOf(
                    HomeScreenSection.UpdatePreferenceRequest(
                        title = "Update State",
                        subTitle = "Do it now"
                    ),
                    HomeScreenSection.HorizontalYojnaSection(

                        yojnaList = listOf(
                            Yojna(
                                id = "yojna-id",
                                name = "yojna-name",
                                artUrl = "https://budgetstockphoto.com/samples/pics/electronic.jpg",
                                bookmarked = false,
                                lastOpenedOn = null,
                                updatedOn = LocalDateTime.now()
                            ),
                            Yojna(
                                id = "yojna-id",
                                name = "yojna-name",
                                artUrl = null,
                                bookmarked = false,
                                lastOpenedOn = null,
                                updatedOn = LocalDateTime.now()
                            ),
                            Yojna(
                                id = "yojna-id",
                                name = "yojna-name yteee",
                                artUrl = "https://budgetstockphoto.com/samples/pics/electronic.jpg",
                                bookmarked = false,
                                lastOpenedOn = null,
                                updatedOn = LocalDateTime.now()
                            )
                        ),
                        sectionMetaData = SectionMetaData(
                            sectionId = "book_marked",
                            title = "Helllo Brothers",
                            selectedLanguage = Language.English,
                            languageInAvailable = listOf(
                                Language.English,
                                Language.Hindi
                            )
                        ),
                        showViewMore = false
                    )
                )
            )

        }

    }


    override fun handleEvent(
        event: HomeScreenViewEvent
    ) {
        when (event) {
            is HomeScreenViewEvent.CategoryClicked -> TODO()
            is HomeScreenViewEvent.YojnaClicked -> openYojnaDetailsScreen()
            HomeScreenViewEvent.RefreshHomeScreen -> TODO()
        }
    }

    private fun openYojnaDetailsScreen() {
        setEffect {
            HomeScreenViewEffects.NavigateTo(NavDestinationYojnaDetailsScreen.createNavigationDestinationRoute (
                yojnaId = "yojna-id"
            ))
        }
    }
}
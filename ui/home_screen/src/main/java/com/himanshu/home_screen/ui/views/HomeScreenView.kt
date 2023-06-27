
package com.himanshu.home_screen.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.himanshu.home_screen.ui.HomeScreenViewEffects
import com.himanshu.home_screen.ui.HomeScreenViewModel
import com.himanshu.home_screen.ui.HomeScreenViewState
import com.himanshu.sarkariyojna.data.home_screen.models.HomeScreenSection


@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navController : NavHostController
){
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = "view_effects_VM"){
        viewModel.effect.collect{
            when (it) {
                is HomeScreenViewEffects.showSnackBar -> TODO()
                is HomeScreenViewEffects.NavigateTo -> navController.navigate(
                    it.navigationEvent.getNavigationDestinationRoute()
                )
            }
        }
    }

    when (val currentState = state) {
        is HomeScreenViewState.ErrorWhileLoadingInitialData -> TODO()
        is HomeScreenViewState.LoadingInitialData -> LoadingHomeScreenData()
        is HomeScreenViewState.ShowSectionOnView -> HomeScreenView(
            refreshingData = currentState.refreshingData,
            sectionData = currentState.sectionData
        )
    }
}

@Composable
fun HomeScreenView(
    refreshingData: Boolean,
    sectionData: List<HomeScreenSection>,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val refreshing by remember { mutableStateOf(refreshingData) }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .pullRefresh(state)
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            this.items(sectionData) {
                HomeScreenSection(it)
            }
        }

//        PullRefreshIndicator(
//            refreshing,
//            state,
//            Modifier.align(Alignment.TopCenter)
//        )
    }
}
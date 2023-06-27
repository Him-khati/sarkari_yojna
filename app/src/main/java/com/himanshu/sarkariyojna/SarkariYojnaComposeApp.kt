package com.himanshu.sarkariyojna

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.himanshu.home_screen.ui.views.HomeScreen
import com.himanshu.sarkari_yojna.destinations.NavDestinationHomeScreen
import com.himanshu.sarkari_yojna.destinations.NavDestinationInitialDecisionMakingScreen
import com.himanshu.sarkari_yojna.destinations.NavDestinationYojnaDetailsScreen
import com.himanshu.sarkari_yojna.NavigationViewModel
import com.himanshu.sarkari_yojna.compose.addComposeDestination
import com.himanshu.sarkariyojna.ui.yojna_details.ui.YojnaDetailsScreen


@Composable
fun SarkariYojnaComposeApp(
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    mainViewModel : MainActivityViewModel = hiltViewModel()
) {

    val navController = rememberNavController()
    SarkariYojnaNavHost(
        navController = navController
    )

    LaunchedEffect(key1 = "key"){

        val navigator = com.himanshu.sarkariyojna.navImpl.Navigator(navController)
        navigationViewModel.navigationEvent.collect{
            navController.navigate(
                it.getNavigationDestinationRoute()
            )
        }
    }

    LaunchedEffect(key1 = "key2"){

        mainViewModel.navigationEvent.collect{
            navController.navigate(
                it.getNavigationDestinationRoute()
            )
        }
    }
}

@Composable
fun SarkariYojnaNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = NavDestinationInitialDecisionMakingScreen.getNavigationDestinationRoute()
    ) {

        addComposeDestination(NavDestinationInitialDecisionMakingScreen) {
            InitialDecisionMakingScreen()
        }

        addComposeDestination(NavDestinationHomeScreen) {
            HomeScreen(
                navController = navController
            )
        }

        addComposeDestination(
            route = NavDestinationYojnaDetailsScreen.createNavigationDestination()
        ) {
            YojnaDetailsScreen()
        }
    }
}

@Composable
fun InitialDecisionMakingScreen() {
    Row(Modifier.fillMaxSize()) {}
}
package com.himanshu.sarkari_yojna.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.himanshu.sarkari_yojna.NavigationDestination

fun NavGraphBuilder.addComposeDestination(
    route: NavigationDestination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route.getNavigationDestinationRoute(),
    arguments,
    deepLinks,
    content
)
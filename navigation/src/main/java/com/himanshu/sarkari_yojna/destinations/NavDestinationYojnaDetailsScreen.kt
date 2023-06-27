package com.himanshu.sarkari_yojna.destinations

import com.himanshu.sarkari_yojna.NavigationDestination

data class NavDestinationYojnaDetailsScreen(
    val navigationRoute: String
) : NavigationDestination {

    override fun getNavigationRouteForRegistering(): String {
       return "yojna_details"
    }

    override fun getNavigationDestinationRoute(): String {
        return navigationRoute
    }

    companion object {

        fun createNavigationDestinationRoute(
            yojnaId: String
        ) = NavDestinationYojnaDetailsScreen(
            navigationRoute = "yojna_details"
        )

        fun createNavigationDestination() = NavDestinationYojnaDetailsScreen(
            navigationRoute = "yojna_details"
        )
    }
}
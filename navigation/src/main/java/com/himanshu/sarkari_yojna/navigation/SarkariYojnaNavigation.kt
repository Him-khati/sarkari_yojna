package com.himanshu.sarkari_yojna.navigation

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions

object SarkariYojnaNavigation {

    object Destinations {

        object YojnaDetailsScreen {
            const val DEEPLINK = ""
        }

        object SelectCategoriesScreen {
            const val DEEPLINK = ""
        }
    }

    fun navigate(
        navController: NavController,
        deepLink: String,
        navOptions: NavOptions? = null
    ) {
        navController.navigate(
            Uri.parse(deepLink),
            getNavOptions(navOptions)
        )
    }

    private fun getNavOptions(
        navOptions: NavOptions?
    ) = navOptions.apply {

    } ?: navOptions {
        this.launchSingleTop = true
    }
}
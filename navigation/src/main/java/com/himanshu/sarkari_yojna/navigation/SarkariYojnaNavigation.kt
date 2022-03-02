package com.himanshu.sarkari_yojna.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions

interface SarkariYojnaNavigation {

    fun navigate(
        navController: NavController,
        actionId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null
    ) = navController.navigate(
        actionId,
        args,
        navOptions ?: navOptions {
            anim {
            }
        }
    )

    fun navigateToSelectLanguageScreenSelectCategories(
        navController: NavController
    )

}
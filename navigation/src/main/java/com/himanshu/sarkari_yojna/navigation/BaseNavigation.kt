package com.himanshu.sarkari_yojna.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions

open class BaseNavigation {

    fun navigate(
        navController: NavController,
        actionId: Int,
        args: Bundle?,
        navOptions: NavOptions?
    ) = navController.navigate(
        actionId,
        args,
        navOptions ?: navOptions {

            anim {

            }
        }
    )
}
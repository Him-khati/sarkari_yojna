package com.himanshu.sarkariyojna.navImpl

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.himanshu.sarkari_yojna.navigation.R

class Navigator constructor(
    private val navHostController: NavHostController,
) {

    fun canGoBack() : Boolean{
        return false
    }

    fun goBack(){

    }

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
                this.enter = R.anim.anim_enter_from_right
                this.exit = R.anim.anim_exit_to_left
                this.popEnter = R.anim.anim_enter_from_left
                this.popExit = R.anim.anim_exit_to_right
            }
        }
    )

    fun navigate(){

    }

}
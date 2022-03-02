package com.himanshu.sarkariyojna.navImpl

import androidx.navigation.NavController
import com.himanshu.sarkari_yojna.navigation.SarkariYojnaNavigation
import com.himanshu.sarkariyojna.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SarkariYojnaNavigationImpl @Inject constructor(): SarkariYojnaNavigation {

    override fun navigateToSelectLanguageScreenSelectCategories(
        navController: NavController
    ) = navigate(
        navController,
        R.id.action_fragment_select_language_to_fragment_select_categories
    )
}
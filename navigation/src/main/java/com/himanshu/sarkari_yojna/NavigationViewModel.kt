package com.himanshu.sarkari_yojna

import android.content.Intent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Navigation Viewmodel, it
 */

class NavigationViewModel : ViewModel() {

    private val _navigationEvent = Channel<NavigationDestination>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun navigateTo(
        destination: NavigationDestination
    ) = _navigationEvent.trySend(
        destination
    )

    /**
     * onNewIntent() of a activity is called when app is in foreground and
     * user clicks a deeplink, gets ACTION_SEND etc
     */
    fun handleOnNewIntent(
        intent: Intent?
    ) {

    }

    fun handleLogOut() {

    }
}
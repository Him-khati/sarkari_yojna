package com.himanshu.sarkariyojna

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.himanshu.sarkari_yojna.DeepLinkParser
import com.himanshu.sarkari_yojna.NavigationDestination
import com.himanshu.sarkari_yojna.destinations.NavDestinationHomeScreen
import com.himanshu.sarkari_yojna.destinations.NavDestinationSelectLanguage
import com.himanshu.sarkari_yojna.domain.useCases.language.GetUserSelectedLanguageUseCase
import com.himanshu.sarkariyojna.core.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getUserSelectedLanguageUseCase: GetUserSelectedLanguageUseCase,
    private val logger: Logger
) : ViewModel() {

    companion object {
        const val TAG = "MainActivityViewModel"
    }

    private val _navigationEvent = Channel<NavigationDestination>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    /**
     * onNewIntent() of a activity is called when app is in foreground and
     * user clicks a deeplink, gets ACTION_SEND etc
     */
    fun lookForDeepLinksAndActions(
        intent: Intent?
    ) = viewModelScope.launch {

        try {
            val hasUserSelectedLanguage = getUserSelectedLanguageUseCase.execute(null) != null

            if (hasUserSelectedLanguage) {
                navigateToHomeScreenAndAttachIntentPayload(
                    intent
                )
            } else {
                navigateToLanguageSelectedScreenAndAttachIntentPayload(
                    intent
                )
            }
        } catch (e: Exception) {
            navigateToHomeScreenAndAttachIntentPayload(
                intent
            )
        }
    }

    private fun navigateToLanguageSelectedScreenAndAttachIntentPayload(
        intent: Intent?
    ) = viewModelScope.launch {
        _navigationEvent.send(
            NavDestinationSelectLanguage
        )
    }

    private fun navigateToHomeScreenAndAttachIntentPayload(
        intent: Intent?
    ) = viewModelScope.launch {
        val destinationReceivedFromDeepLinkPayload = if (intent != null) {
            try {
                DeepLinkParser.parseLinkOrThrow(
                    intent
                )
            } catch (e: Exception) {
                logger.d(
                    TAG,
                    "navigateToHomeScreenAndAttachIntentPayload parsing deeplink resulted in exception",
                    e
                )
                null
            }
        } else {
            null
        }

        _navigationEvent.send(
            NavDestinationHomeScreen
        )
    }

    /**
     * onNewIntent() of a activity is called when app is in foreground and
     * user clicks a deeplink, gets ACTION_SEND etc
     */
    fun handleOnNewIntent(
        intent: Intent?
    ) {
        checkForDeeplink(intent)
    }

    private fun checkForDeeplink(
        intent: Intent?
    ) = viewModelScope.launch {
        if (intent == null) {
            logger.d(
                TAG,
                "checkForDeeplink called with null intent"
            )

            return@launch //TODO when this happens whats should be done ?
        }

        try {
            val destination = DeepLinkParser.parseLinkOrThrow(
                intent
            )
            _navigationEvent.send(destination)
        } catch (e: Exception) {
            logger.d(
                TAG,
                "checkForDeeplink parsing deeplink resulted in exception",
                e
            )
        }
    }


}
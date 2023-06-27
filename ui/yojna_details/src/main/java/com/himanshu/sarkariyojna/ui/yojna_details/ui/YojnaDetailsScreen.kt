package com.himanshu.sarkariyojna.ui.yojna_details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkariyojna.ui.yojna_details.YojnaDetailsContract
import com.himanshu.sarkariyojna.ui.yojna_details.YojnaDetailsViewModel

@Composable
fun YojnaDetailsScreen(
    viewModel: YojnaDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is YojnaDetailsContract.State.ErrorWhileLoadingYojnaDetails -> {}
        YojnaDetailsContract.State.LoadingYojnaDetails -> {}
        is YojnaDetailsContract.State.YojnaDetailsLoadedOrUpdated -> YojnaDetailsMainContent(
            yojnaMetaData = currentState.yojnaMetaData,
            yojnaDetails = currentState.yojnaDetails,
            bionicReadingEnabled = false,
            onBookmarkClicked = {},
            onSelectedLanguageChanged = {},
            pageScrollProgressIndicator = {}
        )
    }
}
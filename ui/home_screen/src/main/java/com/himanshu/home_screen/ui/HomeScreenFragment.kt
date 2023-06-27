package com.himanshu.home_screen.ui

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.himanshu.home_screen.ui.views.HomeScreenView
import com.himanshu.home_screen.ui.views.LoadingHomeScreenData
import com.himanshu.sarkari_yojna.android_common_ui.extensions.composeView
import com.himanshu.sarkariyojna.android_base.base.BaseComposeFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreenFragment : BaseComposeFragment(
    fragmentName = "HomeScreenFragment"
) {
    private val viewModel: HomeScreenViewModel by viewModels()

    override fun createComposeView() = composeView {
        val state by viewModel.uiState.collectAsState()

        when (val currentState = state) {
            is HomeScreenViewState.ErrorWhileLoadingInitialData -> TODO()
            is HomeScreenViewState.LoadingInitialData -> LoadingHomeScreenData()
            is HomeScreenViewState.ShowSectionOnView -> HomeScreenView(
                refreshingData = currentState.refreshingData,
                sectionData = currentState.sectionData
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

    }




}
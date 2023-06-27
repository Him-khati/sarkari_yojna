package com.himanshu.sarkari_yojna.settings.ui.select_language.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.himanshu.sarkari_yojna.settings.ui.select_language.SelectLanguageContract
import com.himanshu.sarkari_yojna.settings.ui.select_language.SelectLanguageViewModel

@Composable
fun SelectLanguageScreen(
    viewModel: SelectLanguageViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val currentLoadingLanguages = state is SelectLanguageContract.State.LoadingLanguages
    val languages = if (state is SelectLanguageContract.State.ShowLanguagesOnView) (state as SelectLanguageContract.State.ShowLanguagesOnView).languages else emptyList()

    SelectLanguageScreenView(
        loadingLanguages = currentLoadingLanguages,
        languages = languages,
        selectedLanguage = null,
        onLanguageClicked = {
            viewModel.setEvent(SelectLanguageContract.Event.LanguageSelected(it))
        }
    )
}



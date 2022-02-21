package com.himanshu.sarkari_yojna.settings.ui.select_language

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.himanshu.sarkari_yojna.domain.useCases.language.ChangeAppLanguageUseCase
import com.himanshu.sarkari_yojna.domain.useCases.language.GetAppLanguagesUseCase
import com.himanshu.sarkari_yojna.settings.R
import com.himanshu.sarkari_yojna.settings.ui.select_language.model.LanguagePresentationModel
import com.himanshu.sarkariyojna.android_base.analytics.Analytics
import com.himanshu.sarkariyojna.android_base.analytics.AnalyticsHelper
import com.himanshu.sarkariyojna.android_base.base.BaseViewModel
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.android_base.network_util.NetworkStateObserver
import com.himanshu.sarkariyojna.core.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectLanguageViewModel @Inject constructor(
    private val changeAppLanguageUseCase: ChangeAppLanguageUseCase,
    private val getAppLanguagesUseCase: GetAppLanguagesUseCase,
    private val logger: Logger,
    private val analyticsHelper: AnalyticsHelper,
    private val networkStateObserver: NetworkStateObserver
) : BaseViewModel<
        SelectLanguageContract.Event,
        SelectLanguageContract.State,
        SelectLanguageContract.Effect>(initialState = SelectLanguageContract.State.LoadingLanguages) {

    companion object {
        const val TAG = "SelectLanguageViewModel"

        val LanguageCardBackgroundMappings = mapOf<Language, Int>(
            Language.English to R.color.language_card_bck_english,
            Language.Hindi to R.color.language_card_bck_hindi
        )
    }

    init {
        startObservingNetworkState()
        getAppLanguages()
    }

    private fun startObservingNetworkState() = viewModelScope.launch {

        networkStateObserver
            .networkState()
            .collect {
                Log.d("NetTAG", it.toString())
            }
    }

    private fun getAppLanguages() = viewModelScope.launch {
        getAppLanguagesUseCase.invoke(null)
            .onSuccess {
                compareLanguagesForBackgroundColorEndEmit(it)
            }.onFailure {

            }
    }

    private fun compareLanguagesForBackgroundColorEndEmit(
        it: List<Language>
    ) {
        val languagesWithColorMappings = it.map {
            LanguagePresentationModel(
                language = it,
                color = LanguageCardBackgroundMappings[it] ?: R.color.material_dynamic_primary50
            )
        }
        setState {
            SelectLanguageContract.State.ShowLanguagesOnView(languagesWithColorMappings)
        }
    }

    override fun handleEvent(
        event: SelectLanguageContract.Event
    ) {
        when (event) {
            is SelectLanguageContract.Event.LanguageSelected -> handleLanguageSelected(
                event.language
            )
        }
    }

    private fun handleLanguageSelected(language: Language) = viewModelScope.launch {
        logger.i(TAG, "[Action] user selected language", language)
        analyticsHelper.logEvent(Analytics.SettingsLanguage.EVENT_LANGUAGE_SELECTED, language)

        try {
            changeAppLanguageUseCase.invoke(language)
        } catch (e: Exception) {
            logger.e(TAG, e, "error while selecting screen")
            setEffect {
                SelectLanguageContract.Effect.ShowToast(
                    false,
                    "Unable to switch language, proceeding with default language"
                )
            }
            delay(500)
        } finally {

            val shouldGoBackToPreviousScreen = false
            if (shouldGoBackToPreviousScreen) {
                logger.i(TAG, "navigating back to previous screen")
                setEffect { SelectLanguageContract.Effect.NavigateBackToPreviousScreen }
            } else {
                logger.i(TAG, "navigating to select state screen")
                setEffect { SelectLanguageContract.Effect.NavigateToSelectCategoriesScreen }
            }
        }
    }
}
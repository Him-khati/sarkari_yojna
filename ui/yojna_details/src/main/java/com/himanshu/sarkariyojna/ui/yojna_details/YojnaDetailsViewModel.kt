package com.himanshu.sarkariyojna.ui.yojna_details

import androidx.lifecycle.viewModelScope
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkari_yojna.domain.useCases.yojna_details.GetYojnaDetailsUseCase
import com.himanshu.sarkari_yojna.domain.useCases.yojna_details.GetYojnaMetaDataUseCase
import com.himanshu.sarkariyojna.android_base.base.BaseViewModel
import com.himanshu.sarkariyojna.android_base.language.LanguagePreferenceManager
import com.himanshu.sarkariyojna.ui.yojna_details.models.YojnaDetailsPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YojnaDetailsViewModel @Inject constructor(
    private val languagePreferenceManager: LanguagePreferenceManager,
    private val getYojnaDetailsUseCase: GetYojnaDetailsUseCase,
    private val getYojnaMetaDataUseCase: GetYojnaMetaDataUseCase
) : BaseViewModel<
        YojnaDetailsContract.Event,
        YojnaDetailsContract.State,
        YojnaDetailsContract.Effect>(initialState = YojnaDetailsContract.State.LoadingYojnaDetails) {

    companion object {
        const val TAG = "YojnaDetailsViewModel"
    }

    init {
        setDefaultLanguage()
    }

    private lateinit var yojnaId: String
    private var languageSelected: Language = Language.English
    private lateinit var yojnaMetaData: YojnaMetaData
    private lateinit var yojnaDetails: YojnaDetails

    private fun setDefaultLanguage() = viewModelScope.launch{
        languageSelected = languagePreferenceManager.getSelectedLanguage()
    }

    override fun handleEvent(
        event: YojnaDetailsContract.Event
    ) = when (event) {
        YojnaDetailsContract.Event.BookmarkYojnaClicked -> bookMarkOrRemoveBookMarkFromYojna()
        is YojnaDetailsContract.Event.ChangeLanguage -> changeYojnaLanguage(
            event.language
        )
        YojnaDetailsContract.Event.ShareYojnaClicked -> openShareYojnaDialog()
        is YojnaDetailsContract.Event.YojnaDetailsScreenInitialised -> checkElseFetchYojnaDetails(
            event.yojnaId
        )
    }

    private fun bookMarkOrRemoveBookMarkFromYojna() {

    }

    private fun openShareYojnaDialog() {
        setEffect { YojnaDetailsContract.Effect.OpenShareMenu(yojnaDetails) }
    }

    private fun changeYojnaLanguage(
        language: Language
    ) {
        languageSelected = language
        checkOrCorrectLanguageSelected()
        fetchYojnaDetails()
    }

    private fun checkElseFetchYojnaDetails(
        yojnaId: String
    ) {
        this.yojnaId = yojnaId
        fetchYojnaMetaDataAndDetails()
    }

    private fun fetchYojnaMetaDataAndDetails() = viewModelScope.launch {

        try {
            setState { YojnaDetailsContract.State.LoadingYojnaDetails }

            yojnaMetaData = getYojnaMetaDataUseCase.resultOrThrow(
                GetYojnaMetaDataUseCase.Parameters(yojnaId)
            )
            checkOrCorrectLanguageSelected()
            yojnaDetails = getYojnaDetailsUseCase.resultOrThrow(
                GetYojnaDetailsUseCase.Parameters(yojnaId, languageSelected)
            )
            processYojnaDetailsAndEmit(yojnaDetails)
        } catch (e: Exception) {
            checkErrorTypeAndEmitUserFriendlyMessage(e)
        }
    }

    private fun checkOrCorrectLanguageSelected() {
        val isYojnaAvailableInUserSelectedLanguage = yojnaMetaData.languageAvailable.find {
            it.isSameAs(languageSelected)
        } != null

        if (isYojnaAvailableInUserSelectedLanguage) {
            return
        }

        //Yojna not available in selected language
        //1. checking if yojna is available in [Language.ENGLISH]
        val isYojnaAvailableInEnglish =
            yojnaMetaData.languageAvailable.find { it.isSameAs(Language.English) } != null
        if (isYojnaAvailableInEnglish) {
            languageSelected = Language.English
            return
        }

        //2. loading yojna in first language available
        if (yojnaMetaData.languageAvailable.isNotEmpty()) {
            languageSelected = yojnaMetaData.languageAvailable.first()
        }
    }

    private fun fetchYojnaDetails() = viewModelScope.launch {

        setState { YojnaDetailsContract.State.LoadingYojnaDetails }
        getYojnaDetailsUseCase
            .invoke(
                GetYojnaDetailsUseCase.Parameters(
                    yojnaId = yojnaId,
                    language = languageSelected
                )
            )
            .onSuccess {
                processYojnaDetailsAndEmit(it)
            }.onFailure {
                checkErrorTypeAndEmitUserFriendlyMessage(it)
            }
    }

    private fun processYojnaDetailsAndEmit(
        yojnaDetails: YojnaDetails
    ) {

        val yojnaPresentationModel = prepareYojnaPresentationModel(yojnaDetails)
        setState { YojnaDetailsContract.State.YojnaDetailsLoadedOrUpdated(yojnaPresentationModel) }
    }

    private fun prepareYojnaPresentationModel(
        yojnaDetails: YojnaDetails
    ): YojnaDetailsPresentationModel {

        return YojnaDetailsPresentationModel(
            yojnaDetails = yojnaDetails,
            languageAvailable = listOf(),
            selectedLanguage = languageSelected
        )
    }

    private fun checkErrorTypeAndEmitUserFriendlyMessage(
        error: Throwable
    ) {
        setState {
            YojnaDetailsContract.State.ErrorWhileLoadingYojnaDetails("Unablt to ")
        }
    }

}
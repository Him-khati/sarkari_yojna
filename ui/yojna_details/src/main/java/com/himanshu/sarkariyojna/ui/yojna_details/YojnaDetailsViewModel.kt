package com.himanshu.sarkariyojna.ui.yojna_details

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
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
    private val savedStateHandle: SavedStateHandle,
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
        viewModelScope.launch {

            fetchYojnaDetailsMock()
//            setDefaultLanguage()
//            fetchYojnaMetaDataAndDetails()
        }
    }

    private fun fetchYojnaDetailsMock() {
        setState {
            YojnaDetailsContract.State.YojnaDetailsLoadedOrUpdated(
                yojnaMetaData = YojnaMetaData(
                    languageAvailable = listOf(Language.English, Language.Hindi),
                ),
                bionicReadingEnabled = false,
                yojnaDetails = YojnaDetails(
                    yojnaId = "yojna_id",
                    category = "Yojna-Category",
                    title = "Yojna tiele sss",
                    coverImage = Uri.parse("https://img.freepik.com/free-photo/book-composition-with-open-book_23-2147690555.jpg?w=740&t=st=1687540157~exp=1687540757~hmac=29c03e068bd3826f80fd3db937a46e4965afd817a67e29eea28d1f05b0d4a88a"),
                    author = "Mr Himanshu",
                    content = "OFSS Bihar Inter Admission Online Application Form 2023 at ofssbihar.in. Students can now apply online for admissions in Intermediate (11th / 12th) Colleges and Schools, download common prospectus and check complete details here. Bihar School Examination Board (BSEB), Patna invites online applications for 11th (+1) or 12th (+2) Colleges & School Admissions through OFSS Bihar",
                    bookmarked = false
                )
            )
        }
    }

    private lateinit var yojnaId: String
    private var languageSelected: Language = Language.English
    private lateinit var yojnaMetaData: YojnaMetaData
    private lateinit var yojnaDetails: YojnaDetails

    private suspend fun setDefaultLanguage() {
        languageSelected = languagePreferenceManager.getSelectedLanguage() ?: languagePreferenceManager.getDefaultAppLanguage()
    }

    override fun handleEvent(
        event: YojnaDetailsContract.Event
    ) = when (event) {
        YojnaDetailsContract.Event.BookmarkYojnaClicked -> bookMarkOrRemoveBookMarkFromYojna()
        is YojnaDetailsContract.Event.ChangeLanguage -> changeYojnaLanguage(
            event.language
        )
        YojnaDetailsContract.Event.ShareYojnaClicked -> openShareYojnaDialog()
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

        setState { YojnaDetailsContract.State.YojnaDetailsLoadedOrUpdated(
            yojnaMetaData = yojnaMetaData,
            yojnaDetails = yojnaDetails,
            bionicReadingEnabled = false
        ) }
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
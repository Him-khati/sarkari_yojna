package com.himanshu.sarkari_yojna.settings.ui.select_language

import com.himanshu.sarkari_yojna.settings.ui.select_language.model.LanguagePresentationModel
import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkariyojna.android_base.base.UiEffect
import com.himanshu.sarkariyojna.android_base.base.UiEvent
import com.himanshu.sarkariyojna.android_base.base.UiState

class SelectLanguageContract {

    sealed class Event : UiEvent {

        data class LanguageSelected(
            val language : Language
        ) : Event()
    }

    sealed class State : UiState {

        object LoadingLanguages : State()

        data class ShowLanguagesOnView(
            val languages: List<LanguagePresentationModel>
        ) : State()

    }

    sealed class Effect : UiEffect {

        object NavigateToSelectStateScreen : Effect()

        object NavigateBackToPreviousScreen : Effect()

        data class ShowToast(
            val showLongToast : Boolean,
            val message : String
        ) : Effect()
    }
}
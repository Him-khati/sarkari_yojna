package com.himanshu.sarkariyojna.ui.yojna_details

import com.himanshu.sarkariyojna.android_base.language.Language
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaDetails
import com.himanshu.sarkari_yojna.domain.models.yojna.YojnaMetaData
import com.himanshu.sarkariyojna.android_base.base.UiEffect
import com.himanshu.sarkariyojna.android_base.base.UiEvent
import com.himanshu.sarkariyojna.android_base.base.UiState
import com.himanshu.sarkariyojna.ui.yojna_details.models.YojnaDetailsPresentationModel

class YojnaDetailsContract {

    sealed class Event : UiEvent {

        object BookmarkYojnaClicked : Event()
        object ShareYojnaClicked : Event()
        data class ChangeLanguage(
            val language: Language
        ) : Event()
    }

    sealed class State : UiState {

        object LoadingYojnaDetails : State()

        data class YojnaDetailsLoadedOrUpdated(
            val yojnaMetaData: YojnaMetaData,
            val yojnaDetails: YojnaDetails,
            val bionicReadingEnabled: Boolean
        ) : State()

        data class ErrorWhileLoadingYojnaDetails(
            val error: String
        ) : State()
    }

    sealed class Effect : UiEffect {

        data class OpenShareMenu(
            val yojna: YojnaDetails
        ) : Effect()
    }
}
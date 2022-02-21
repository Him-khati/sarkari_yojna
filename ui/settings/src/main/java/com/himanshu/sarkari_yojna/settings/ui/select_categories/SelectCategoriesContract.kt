package com.himanshu.sarkari_yojna.settings.ui.select_categories

import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.settings.ui.select_language.model.LanguagePresentationModel
import com.himanshu.sarkariyojna.android_base.base.UiEffect
import com.himanshu.sarkariyojna.android_base.base.UiEvent
import com.himanshu.sarkariyojna.android_base.base.UiState

class SelectCategoriesContract {

    sealed class Event : UiEvent {

        data class CategorySelectedOrUnSelected(
            val category : YojnaCategory
        ) : Event()

        object NextButtonClicked : Event()
    }

    sealed class State : UiState {

        object LoadingOrRefreshingCategories : State()

        data class ShowOrUpdateCategoriesOnView(
            val categories: List<YojnaCategoryPresentationModel>
        ) : State()

        data class ErrorWhileLoadingCategories(
            val error : String
        ) : State()

        object SavingSelectedCategories : State()

        data class UnableToSaveCategories(
            val error: String
        ) : State()
    }

    sealed class Effect : UiEffect {

        object NavigateToPreviousScreen : Effect()

        object NavigateToSelectAgeScreen : Effect()

        data class ShowToast(
            val showLongToast : Boolean,
            val message : String
        ) : Effect()
    }
}
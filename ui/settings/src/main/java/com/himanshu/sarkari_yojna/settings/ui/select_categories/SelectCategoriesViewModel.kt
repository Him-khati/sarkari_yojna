package com.himanshu.sarkari_yojna.settings.ui.select_categories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.domain.useCases.categories.GetYojnaCategoriesUseCase
import com.himanshu.sarkari_yojna.domain.useCases.categories.SaveYojnaCategoriesUseCase
import com.himanshu.sarkariyojna.android_base.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCategoriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getYojnaCategoriesUseCase: GetYojnaCategoriesUseCase,
    private val saveYojnaCategoriesUseCase: SaveYojnaCategoriesUseCase
) : BaseViewModel<
        SelectCategoriesContract.Event,
        SelectCategoriesContract.State,
        SelectCategoriesContract.Effect>(
    initialState = SelectCategoriesContract.State.LoadingOrRefreshingCategories
) {

    companion object {
        private const val TAG = "SelectCategoriesViewModel"
        private const val INTENT_SELECTED_CATEGORIES = "selected_categories"
    }

    private var selectedCategories: MutableList<YojnaCategory> = mutableListOf()

    init {
        subscribeToYojnaCategories()
    }

    private fun subscribeToYojnaCategories() = viewModelScope.launch {

        getYojnaCategoriesUseCase
            .resultOrThrow(null)
            .distinctUntilChanged()
            .onEach { checkIfCategoryIsAlreadySelected(it) }
            .onStart {
                setState { SelectCategoriesContract.State.LoadingOrRefreshingCategories }
            }
            .catch {
                setState { SelectCategoriesContract.State.ErrorWhileLoadingCategories("Unable to refresh categories") }
            }
            .collect {
                setState { SelectCategoriesContract.State.ShowOrUpdateCategoriesOnView(it) }
            }
    }

    private fun checkIfCategoryIsAlreadySelected(
        it: List<YojnaCategoryPresentationModel>
    ) = it.onEach {
        if (!it.selected) {
            it.selected = selectedCategories.find { category ->
                category.id == it.category.id
            } != null
        }
    }


    override fun handleEvent(
        event: SelectCategoriesContract.Event
    ) {
        when (event) {
            is SelectCategoriesContract.Event.CategorySelectedOrUnSelected -> selectOrUnselect(
                event.category
            )
            SelectCategoriesContract.Event.NextButtonClicked -> saveUserSelectedCategories()
        }
    }

    private fun saveUserSelectedCategories() = viewModelScope.launch {
        setState { SelectCategoriesContract.State.SavingSelectedCategories }

        saveYojnaCategoriesUseCase.invoke(
            selectedCategories
        ).onSuccess {
            setEffect { SelectCategoriesContract.Effect.NavigateToSelectAgeScreen }
        }.onFailure {
            setEffect { SelectCategoriesContract.Effect.ShowToast(false,"Unable to save categories") }
            delay(200)
            setEffect { SelectCategoriesContract.Effect.NavigateToSelectAgeScreen }
        }
    }

    private fun selectOrUnselect(
        category: YojnaCategory
    ) {
        if (selectedCategories.contains(category)) {
            selectedCategories.remove(category)
        } else {
            selectedCategories.add(category)
        }

        savedStateHandle.set(
            INTENT_SELECTED_CATEGORIES,
            selectedCategories
        )
    }
}
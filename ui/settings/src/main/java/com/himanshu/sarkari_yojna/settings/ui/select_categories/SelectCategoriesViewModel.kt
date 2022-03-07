package com.himanshu.sarkari_yojna.settings.ui.select_categories

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategory
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.domain.useCases.categories.GetYojnaCategoriesUseCase
import com.himanshu.sarkari_yojna.domain.useCases.categories.SaveYojnaCategoriesUseCase
import com.himanshu.sarkari_yojna.domain.useCases.categories.UpdateYojnaCategoriesUseCase
import com.himanshu.sarkariyojna.android_base.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCategoriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getYojnaCategoriesUseCase: GetYojnaCategoriesUseCase,
    private val saveYojnaCategoriesUseCase: SaveYojnaCategoriesUseCase,
    private val updateYojnaCategoriesUseCase: UpdateYojnaCategoriesUseCase
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

        updateYojnaCategoriesUseCase
            .invoke(null)
            .combine(getYojnaCategoriesUseCase.invoke(null)) { updateCategoriesState, categories ->

                if (updateCategoriesState is UpdateYojnaCategoriesUseCase.State.CategoriesUpdating) {
                    //Case 1 .Categories are being updated
                    setState { SelectCategoriesContract.State.LoadingOrRefreshingCategories }
                } else {
                    //Case 2 . Categories have been updated or there was an error while updating categories
                    if (updateCategoriesState is UpdateYojnaCategoriesUseCase.State.CategoriesUpdated) {
                        setState {
                            SelectCategoriesContract.State.ShowOrUpdateCategoriesOnView(
                                categories
                            )
                        }
                    } else if (updateCategoriesState is UpdateYojnaCategoriesUseCase.State.ErrorWhileUpdatingCategories) {

                        if (categories.isNotEmpty()) {
                            //Categories Update failed but we have some cached data, so we will show that data to user
                            setState {
                                SelectCategoriesContract.State.ShowOrUpdateCategoriesOnView(
                                    categories
                                )
                            }
                        } else {

                            setState {
                                SelectCategoriesContract.State.ErrorWhileLoadingCategories(
                                    updateCategoriesState.error
                                )
                            }
                        }
                    }
                }
            }.collect()

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
            setEffect {
                SelectCategoriesContract.Effect.ShowToast(
                    false,
                    "Unable to save categories"
                )
            }
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
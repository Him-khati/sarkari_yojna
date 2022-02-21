package com.himanshu.sarkari_yojna.settings.ui.select_categories

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.himanshu.sarkari_yojna.domain.models.yojna_category.YojnaCategoryPresentationModel
import com.himanshu.sarkari_yojna.settings.R
import com.himanshu.sarkari_yojna.settings.databinding.SelectCategoriesFragmentBinding
import com.himanshu.sarkariyojna.android_base.base.BaseFragment
import com.himanshu.sarkariyojna.core.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class SelectCategoriesFragment : BaseFragment<SelectCategoriesFragmentBinding>(
    fragmentName = TAG,
    layoutId = R.layout.select_categories_fragment
) {
    companion object {
        const val TAG = "SelectCategoriesFragment"
    }

    @Inject
    lateinit var logger : Logger
    private val viewModel: SelectCategoriesViewModel by viewModels()

    override fun onViewCreatedOrRestored(
        viewBinding: SelectCategoriesFragmentBinding,
        viewCreatedForTheFirstTime: Boolean
    ) {

        if (viewCreatedForTheFirstTime) {
            initViewModel()
        }
    }

    private fun initViewModel() {

        lifecycleScope.launchWhenCreated {

            viewModel
                .uiState
                .collect {

                    when (it) {
                        is SelectCategoriesContract.State.ErrorWhileLoadingCategories -> errorWhileLoadingCategories(it.error)
                        SelectCategoriesContract.State.LoadingOrRefreshingCategories -> showLoadingCategories()
                        SelectCategoriesContract.State.SavingSelectedCategories -> TODO()
                        is SelectCategoriesContract.State.ShowOrUpdateCategoriesOnView -> showCategoriesOnView(it.categories)
                        is SelectCategoriesContract.State.UnableToSaveCategories -> TODO()
                    }
                }
        }

        lifecycleScope.launchWhenCreated {

            viewModel
                .effect
                .collectLatest {

                    when (it) {
                        SelectCategoriesContract.Effect.NavigateToPreviousScreen -> TODO()
                        SelectCategoriesContract.Effect.NavigateToSelectAgeScreen -> TODO()
                        is SelectCategoriesContract.Effect.ShowToast -> TODO()
                    }
                }
        }
    }

    private fun showCategoriesOnView(
        categories: List<YojnaCategoryPresentationModel>
    ) {
        logger.i(TAG,"showing ${categories.size} categories on view...")
        logger.d(TAG,"showCategoriesOnView() categories shown on view :",categories)
    }

    private fun errorWhileLoadingCategories(error: String) {
        logger.d(TAG,"errorWhileLoadingCategories() $error")
    }

    private fun showLoadingCategories() {
        logger.i(TAG,"showLoadingCategories() loading views...")
    }
}
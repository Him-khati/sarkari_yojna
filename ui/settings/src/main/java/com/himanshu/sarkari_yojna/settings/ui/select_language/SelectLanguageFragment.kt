package com.himanshu.sarkari_yojna.settings.ui.select_language

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.himanshu.sarkari_yojna.settings.R
import com.himanshu.sarkari_yojna.settings.databinding.SelectLanguageFragmentBinding
import com.himanshu.sarkariyojna.android_base.base.BaseFragment
import com.himanshu.sarkariyojna.android_base.language.Language
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectLanguageFragment : BaseFragment<SelectLanguageFragmentBinding>(
    fragmentName = TAG,
    layoutId = R.layout.select_language_fragment
), LanguageAdapter.LanguageItemClickListener {

    companion object {
        const val TAG = "SelectLanguageFragment"
    }

    private val viewModel: SelectLanguageViewModel by viewModels()

    private val languageAdapter: LanguageAdapter by lazy {
        LanguageAdapter(this@SelectLanguageFragment)
    }

    override fun onViewCreatedOrRestored(
        viewBinding: SelectLanguageFragmentBinding,
        viewCreatedForTheFirstTime: Boolean
    ) {

        if (viewCreatedForTheFirstTime) {
            initViews(viewBinding)
            initViewModel()
        }
    }

    private fun initViews(
        viewBinding: SelectLanguageFragmentBinding
    ) {
        viewBinding.languageRecyclerView.apply {
            layoutManager = GridLayoutManager(
                requireContext(),
                2
            )
            itemAnimator = DefaultItemAnimator()
            isMotionEventSplittingEnabled = false
            setHasFixedSize(true)
            adapter = languageAdapter
        }

    }

    private fun initViewModel() {

        lifecycleScope.launchWhenCreated {

            viewModel.uiState.collect {
                when (it) {
                    SelectLanguageContract.State.LoadingLanguages -> {}
                    is SelectLanguageContract.State.ShowLanguagesOnView -> languageAdapter.setLanguages(
                        it.languages
                    )
                }
            }
        }

        lifecycleScope.launchWhenCreated {

            viewModel.effect.collect {
                when (it) {
                    SelectLanguageContract.Effect.NavigateBackToPreviousScreen -> {
                        findNavController().navigateUp()
                    }
                    SelectLanguageContract.Effect.NavigateToSelectStateScreen -> {

                    }
                    is SelectLanguageContract.Effect.ShowToast -> Toast.makeText(
                        requireContext(),
                        it.message,
                        if (it.showLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onLanguageClicked(
        language: Language
    ) {
        viewModel.setEvent(
            SelectLanguageContract.Event.LanguageSelected(language)
        )
    }
}
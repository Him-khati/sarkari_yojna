package com.himanshu.sarkariyojna.ui.yojna_details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.himanshu.sarkariyojna.android_base.base.BaseFragment
import com.himanshu.sarkariyojna.ui.yojna_details.databinding.FragmentYojnaDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YojnaDetailsFragment : BaseFragment<FragmentYojnaDetailsBinding>(
    fragmentName = TAG,
    layoutId = R.layout.fragment_yojna_details
) {
    companion object {
        const val TAG = "YojnaDetailsFragment"
    }

    private val viewModel: YojnaDetailsViewModel by viewModels()

    override fun onViewCreatedOrRestored(
        viewBinding: FragmentYojnaDetailsBinding,
        viewCreatedForTheFirstTime: Boolean
    ) {

        if (viewCreatedForTheFirstTime) {
            initViewModel()
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {

            viewModel.uiState.collect {

                when (it) {
                    is YojnaDetailsContract.State.ErrorWhileLoadingYojnaDetails -> {}
                    YojnaDetailsContract.State.LoadingYojnaDetails -> {}
                    is YojnaDetailsContract.State.YojnaDetailsLoadedOrUpdated -> {}
                }
            }
        }

        lifecycleScope.launchWhenCreated {

            viewModel.effect.collect {
                when (it) {
                    is YojnaDetailsContract.Effect.OpenShareMenu -> {}
                }
            }
        }
    }


}
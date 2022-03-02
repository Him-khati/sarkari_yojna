package com.himanshu.home_screen.ui

import androidx.fragment.app.viewModels
import com.himanshu.home_screen.R
import com.himanshu.home_screen.databinding.HomeScreenFragmentBinding
import com.himanshu.sarkariyojna.android_base.base.BaseFragment

class HomeScreenFragment : BaseFragment<HomeScreenFragmentBinding>(
    fragmentName = TAG,
    layoutId = R.layout.home_screen_fragment
) {
    companion object {
        const val TAG = "HomeScreenFragment"
    }

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onViewCreatedOrRestored(
        viewBinding: HomeScreenFragmentBinding,
        viewCreatedForTheFirstTime: Boolean
    ) {
    }
}
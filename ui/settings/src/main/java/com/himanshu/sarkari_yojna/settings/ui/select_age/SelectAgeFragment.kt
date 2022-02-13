package com.himanshu.sarkari_yojna.settings.ui.select_age

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.himanshu.sarkari_yojna.settings.R
import com.himanshu.sarkari_yojna.settings.databinding.SelectAgeFragmentBinding
import com.himanshu.sarkariyojna.android_base.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectAgeFragment : BaseFragment<SelectAgeFragmentBinding>(
    fragmentName = TAG,
    layoutId = R.layout.select_age_fragment
) {

    companion object {
        const val TAG = "SelectAgeFragment"
        fun newInstance() = SelectAgeFragment()
    }

    private val viewModel: SelectAgeViewModel by viewModels()

    override fun onViewCreatedOrRestored(
        viewBinding: SelectAgeFragmentBinding,
        viewCreatedForTheFirstTime: Boolean
    ) {
        TODO("Not yet implemented")
    }
}
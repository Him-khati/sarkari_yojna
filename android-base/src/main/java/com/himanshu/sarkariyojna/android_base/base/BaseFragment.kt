package com.himanshu.sarkariyojna.android_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.himanshu.sarkariyojna.core.logger.Logger
import dagger.hilt.EntryPoint
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<V : ViewDataBinding>(
    private val fragmentName: String,
    @LayoutRes private val layoutId: Int
) : Fragment() {

    private val logger: Logger = Logger()
    private lateinit var _viewDataBinding: V

    val logTag: String
        get() {
            return fragmentName
        }

    val viewBinding: V
        get() {
            return _viewDataBinding
        }

    private var _viewCreatedForTheFirstTime = false
    val viewCreatedForTheFirstTime get() = _viewCreatedForTheFirstTime


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logger.d(fragmentName, "onCreateView()")

        if (::_viewDataBinding.isInitialized.not()) {
            _viewCreatedForTheFirstTime = true

            _viewDataBinding = DataBindingUtil.inflate(
                inflater,
                layoutId,
                container,
                false
            )
        } else {
            _viewCreatedForTheFirstTime = false
        }

        return _viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logger.d(fragmentName, "viewCreated()")
        onViewCreatedOrRestored(_viewDataBinding, _viewCreatedForTheFirstTime)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logger.d(fragmentName, "onSaveInstanceState()")
    }

    override fun onResume() {
        super.onResume()
        logger.d(fragmentName, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        logger.d(fragmentName, "onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logger.d(fragmentName, "onDestroy()")
    }

    abstract fun onViewCreatedOrRestored(
        viewBinding: V,
        viewCreatedForTheFirstTime : Boolean
    )
}
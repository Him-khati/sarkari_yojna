package com.himanshu.sarkariyojna.android_base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.himanshu.sarkariyojna.core.logger.Logger

abstract class BaseComposeFragment(
    private val fragmentName: String
) : Fragment() {

    val logTag: String
        get() {
            return fragmentName
        }

    private val logger = Logger()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        logger.d(fragmentName, "onCreateView()")
        return createComposeView()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logger.d(fragmentName, "viewCreated()")
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

    abstract fun createComposeView(): ComposeView
}
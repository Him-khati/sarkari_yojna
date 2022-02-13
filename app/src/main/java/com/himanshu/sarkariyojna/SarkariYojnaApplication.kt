package com.himanshu.sarkariyojna

import android.app.Application
import com.himanshu.sarkariyojna.core.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SarkariYojnaApplication : Application(){

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        logger.initLogging(BuildConfig.DEBUG)
    }
}
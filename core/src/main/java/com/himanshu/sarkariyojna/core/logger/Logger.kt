package com.himanshu.sarkariyojna.core.logger

import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Logger @Inject constructor() {

    fun initLogging(
        debugMode: Boolean
    ) {
        if (debugMode) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }

    fun d(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.d(tag, message, args)
    }

    fun d(
        tag: String,
        t: Throwable,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.d(t, message, args)
    }

    fun e(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.e(message, args)
    }

    fun e(
        tag: String,
        t: Throwable,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.e(t, message, args)
    }


    fun i(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.i(message, args)
    }


    fun i(
        tag: String,
        t: Throwable,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.i(t, message, args)
    }


    fun v(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.v(message, args)
    }

    fun v(
        tag: String,
        t: Throwable
    ) {
        Timber.tag(tag)
        Timber.v(t)
    }

    fun v(
        tag: String,
        t: Throwable,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.v(t, message, args)
    }

    fun w(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.w(message, args)
    }


    fun w(
        tag: String,
        message: String,
        t: Throwable,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.w(t, message, args)
    }

    fun wtf(
        tag: String,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.wtf(message, args)
    }

    fun wtf(
        tag: String,
        t: Throwable,
        message: String,
        vararg args: Any
    ) {
        Timber.tag(tag)
        Timber.wtf(t, message, args)
    }

    fun setUserInfoForLogger(
        userIdentifier: String,
        userName: String,
        userEmail: String?
    ) {
//        firebaseCrashlytics.setUserId(userIdentifier)
//        firebaseCrashlytics.setCustomKey("name", userName)
//        firebaseCrashlytics.setCustomKey("email", userIdentifier)
    }

    fun unBindUserFromLogger() {}
}
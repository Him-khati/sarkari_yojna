package com.himanshu.sarkariyojna.core.exception

class LoggingAwareException constructor(
    loggable: Boolean,
    message: String,
    cause: Throwable?
) : Exception(
    message,
    cause
) {
    val isLoggable = loggable
}


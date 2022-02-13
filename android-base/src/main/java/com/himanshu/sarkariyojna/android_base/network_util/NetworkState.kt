package com.himanshu.sarkariyojna.android_base.network_util

sealed class NetworkStates {

    object Unknown : NetworkStates()

    object Connected : NetworkStates()

    object ConnectedButNoInternet : NetworkStates()

    object Disconnected : NetworkStates()

    object Disconnecting : NetworkStates()

    data class NetworkChanged(
        val currentNetworkType : NetworkType
    ) : NetworkStates()
}
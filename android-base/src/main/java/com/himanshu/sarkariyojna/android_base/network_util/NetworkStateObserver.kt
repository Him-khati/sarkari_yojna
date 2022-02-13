package com.himanshu.sarkariyojna.android_base.network_util

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.himanshu.sarkariyojna.core.logger.Logger
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateObserver @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    private val logger: Logger
) {

    companion object {
        private const val TAG = "NetworkStateObserver"
    }

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    fun networkState() = networkStateUnfiltered()
        .distinctUntilChanged()

    private fun networkStateUnfiltered(): Flow<NetworkStates> = callbackFlow {

        val networkStateCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySend(
                    NetworkStates.Connected
                )
                logger.i(TAG, "onAvailable()")
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
                trySend(
                    NetworkStates.Disconnecting
                )
                logger.i(TAG, "onLosing()")
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySend(
                    NetworkStates.Disconnected
                )
                logger.i(TAG, "onLost()")
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySend(
                    NetworkStates.ConnectedButNoInternet
                )
                logger.i(TAG, "onUnavailable()")
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val hasCellular = networkCapabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                )
                val hasWifi = networkCapabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )

                val networkType = when {
                    hasCellular -> NetworkType.CELLULAR
                    hasWifi -> NetworkType.WIFI
                    else -> NetworkType.UNKNOWN
                }

                trySend(
                    NetworkStates.NetworkChanged(
                        networkType
                    )
                )
                logger.i(TAG, "onCapabilitiesChanged()")
            }
        }

        //Registering Callback ....
        logger.i(TAG, "registering network state listener [${networkStateCallback.hashCode()}]...")
        connectivityManager.requestNetwork(
            networkRequest,
            networkStateCallback
        )

        awaitClose {
            logger.i(
                TAG,
                "unregistering network state listener [${networkStateCallback.hashCode()}]..."
            )
            connectivityManager.unregisterNetworkCallback(networkStateCallback)
        }
    }
}
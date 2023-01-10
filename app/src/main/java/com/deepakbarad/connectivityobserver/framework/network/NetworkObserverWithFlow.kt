package com.deepakbarad.connectivityobserver.framework.network

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object NetworkObserverWithFlow : ConnectivityManager.NetworkCallback() {

    private val mIsConnectedToNetworkFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isConnectedToNetworkFlow: StateFlow<Boolean> = mIsConnectedToNetworkFlow.asStateFlow()

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        mIsConnectedToNetworkFlow.update {
            true
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        mIsConnectedToNetworkFlow.update {
            false
        }
    }
}
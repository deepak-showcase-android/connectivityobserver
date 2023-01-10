package com.deepakbarad.connectivityobserver.framework.network

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object NetworkObserverWithFlow : ConnectivityManager.NetworkCallback(), DefaultLifecycleObserver {

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

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        mIsConnectedToNetworkFlow.update {
            false
        }
    }
}
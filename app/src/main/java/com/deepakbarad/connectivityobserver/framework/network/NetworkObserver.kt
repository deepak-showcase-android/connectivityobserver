package com.deepakbarad.connectivityobserver.framework.network

import android.net.ConnectivityManager
import android.net.Network

object NetworkObserver : ConnectivityManager.NetworkCallback() {

    private val subscribers: MutableList<INetworkObserver> = mutableListOf()

    fun subscribe(subscriber: INetworkObserver) {
        subscribers.add(subscriber)
    }

    fun unSubscribe(subscriber: INetworkObserver) {
        subscribers.remove(subscriber)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        subscribers.forEach { it.onConnected() }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        subscribers.forEach { it.onDisconnected() }
    }
}

interface INetworkObserver {
    fun onConnected()
    fun onDisconnected()
}
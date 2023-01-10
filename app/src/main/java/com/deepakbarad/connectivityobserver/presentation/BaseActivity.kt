package com.deepakbarad.connectivityobserver.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.deepakbarad.connectivityobserver.framework.interfaces.INetworkObserver
import com.deepakbarad.connectivityobserver.framework.network.NetworkObserver

open class BaseActivity : AppCompatActivity(), INetworkObserver {

    override fun onStart() {
        super.onStart()
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerDefaultNetworkCallback(
            NetworkObserver
        )
        NetworkObserver.subscribe(this)
    }

    override fun onStop() {
        super.onStop()
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).unregisterNetworkCallback(
            NetworkObserver
        )
        NetworkObserver.unSubscribe(this)
    }

    override fun onConnected() {
        onConnectivityChange(true)
    }

    override fun onDisconnected() {
        onConnectivityChange(false)
    }

    open fun onConnectivityChange(isConnected: Boolean) {}
}
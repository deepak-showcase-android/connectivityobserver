package com.deepakbarad.connectivityobserver.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.deepakbarad.connectivityobserver.framework.network.NetworkObserverWithFlow
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(NetworkObserverWithFlow)
        setObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(NetworkObserverWithFlow)
    }

    override fun onStart() {
        super.onStart()
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).registerDefaultNetworkCallback(
            NetworkObserverWithFlow
        )
    }

    override fun onStop() {
        super.onStop()
        (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).unregisterNetworkCallback(
            NetworkObserverWithFlow
        )
    }

    open fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                NetworkObserverWithFlow.isConnectedToNetworkFlow.collect {
                    onConnectivityChange(it)
                }
            }
        }
    }

    open fun onConnectivityChange(isConnected: Boolean) {}
}
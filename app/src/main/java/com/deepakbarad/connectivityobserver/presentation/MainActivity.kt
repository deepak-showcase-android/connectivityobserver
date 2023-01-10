package com.deepakbarad.connectivityobserver.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deepakbarad.connectivityobserver.R
import com.deepakbarad.connectivityobserver.databinding.ActivityMainBinding
import com.deepakbarad.connectivityobserver.framework.network.NetworkObserver
import com.google.android.material.snackbar.Snackbar

class MainActivity : BaseActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onConnectivityChange(isConnected: Boolean) {
        super.onConnectivityChange(isConnected)
        if (isConnected) {
            Snackbar.make(binding.tvTitle, "Connected", Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.tvTitle, "Disconnected", Snackbar.LENGTH_SHORT).show()
        }
    }
}
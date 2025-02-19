package com.ismaelo.apiapp.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ConnectivityObserver(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val _isConnected = MutableStateFlow(false)

    val isConnected: StateFlow<Boolean> = _isConnected

    init {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                super.onAvailable(network)
                _isConnected.value = true
            }

            override fun onLost(network: android.net.Network) {
                super.onLost(network)
                _isConnected.value = false
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        checkInitialConnection()
    }

    private fun checkInitialConnection() {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        _isConnected.value =
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}

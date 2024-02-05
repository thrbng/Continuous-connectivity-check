@file:Suppress("DEPRECATION")

package com.example.stockshaper.baseActivityInternet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReciver: BroadcastReceiver() {
    override fun onReceive(context: Context, arg1: Intent?) {
        if (connectivityReciverListener != null) {
            connectivityReciverListener!!.onNetworkConnectionChanged(isConnectedOrCoonecting(context))
        }
    }

    private fun isConnectedOrCoonecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    interface ConnectivityReciverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReciverListener: ConnectivityReciverListener? = null
    }
}
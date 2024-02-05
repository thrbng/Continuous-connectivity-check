@file:Suppress("DEPRECATION")

package com.example.stockshaper.baseActivityInternet

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity(), ConnectivityReciver.ConnectivityReciverListener {

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReciver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {
            showNoConnectionDialog()
        } else {
            dismissNoConnectionDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReciver.connectivityReciverListener = this
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

    private fun showNoConnectionDialog() {
        if (alertDialog == null) {
            alertDialog = AlertDialog.Builder(this)
                .setTitle("No Internet Connection")
                .setMessage("Please check your internet connection.")
                .setCancelable(false)
                .create()

            alertDialog?.show()
        }
    }

    private fun dismissNoConnectionDialog() {
        alertDialog?.dismiss()
        alertDialog = null
    }
}
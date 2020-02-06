package com.example.firstkotlinapp

import android.content.Context
import androidx.fragment.app.Fragment
import android.net.NetworkInfo
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager



fun Context.showToast(message:String,duration:Int= android.widget.Toast.LENGTH_SHORT){
    android.widget.Toast.makeText(this, message , duration).show()
}

fun Context.isInternetAvailable(): Boolean {

    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = manager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
package com.example.myapplication.base.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.application.LCApplication
import com.google.gson.JsonSyntaxException
import isNetWorkActive
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun Throwable?.parseErrorString(): String {
    return when (this) {
        is SocketException -> getString(R.string.SocketException)
        is ConnectException -> getString(R.string.ConnectException)
        is UnknownHostException -> getString(R.string.UnknownHostException)
        is JsonSyntaxException -> getString(R.string.JsonSyntaxException)
        is SocketTimeoutException -> getString(R.string.SocketTimeoutException)
        is TimeoutException -> getString(R.string.SocketTimeoutException)
        is IllegalArgumentException -> {
            if (message?.contains("baseUrl must end in ") == true)
                if (LCApplication.application.isNetWorkActive()) {
                    getString(R.string.HostBaseUrlError)
                } else {
                    getString(R.string.Mobilenetuseless_msg)
                }
            else message ?: getString(R.string.ElseNetException)
        }
        else -> getString(R.string.ElseNetException)
    }
}


fun <T> Flow<T>.flowOnIOAndcatch(errorMsgLiveData: MutableLiveData<String>): Flow<T> {
    return flowOn(Dispatchers.IO)
        .catch {
            it.printStackTrace()
            errorMsgLiveData.value = it.parseErrorString();
        }
}

fun getString(resID: Int) = LCApplication.application.getString(resID)

fun ImageView.loadUrl(url :String)=  Glide.with(context)
    .load(url)
    .into(this)


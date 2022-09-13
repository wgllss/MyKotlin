package com.example.myapplication.base.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.DialogBean
import kotlinx.coroutines.CoroutineScope

open class BaseViewModel : ViewModel() {
    val showDialog: MutableLiveData<DialogBean> = MutableLiveData()
    val errorMsgLiveData: MutableLiveData<String> = MutableLiveData()

    fun launch2(): CoroutineScope {
        Log.e(javaClass.simpleName, "线程-launch2-->${Thread.currentThread().name}")
        return viewModelScope
    }

    open fun start() {}
}
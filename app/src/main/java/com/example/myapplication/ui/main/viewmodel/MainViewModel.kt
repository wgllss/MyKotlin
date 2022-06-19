package com.example.myapplication.ui.main.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.net.RetrofitClient
import com.example.myapplication.ui.main.repository.BaiduRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

open class MainViewModel() : BaseViewModel() {
    val listData by lazy { MutableLiveData<ArrayList<BaiduDataBean>?>() }
    val liveDataPn by lazy { MutableLiveData<Int>() }
    val liveDataGsm by lazy { MutableLiveData<String>() }
    val searchContent by lazy { MutableLiveData<String>() }
    private val repository by lazy { BaiduRepository() }


//    init {
//        liveDataPn.value = 0
//        liveDataGsm.value = ""
//
//    }

    fun getImageList() {
        Log.e(javaClass.simpleName, "线程-1-->${Thread.currentThread().name}")
        viewModelScope.launch {
            Log.e(javaClass.simpleName, "线程-2-->${Thread.currentThread().name}")
            repository.get89(searchContent.value!!, searchContent.value!!, liveDataPn.value!!, liveDataGsm.value!!)
                .collect {
                    Log.e(javaClass.simpleName, "线程-3-->${Thread.currentThread().name}")
                    if (liveDataPn.value == 0) listData.value = it?.data
                    else listData.value = listData.value?.also { ims ->
                        ims.addAll(it.data)
                    }
                    liveDataPn.value = liveDataPn.value?.plus(30)
                    liveDataGsm.value = it.gsm
                }
        }
    }
}
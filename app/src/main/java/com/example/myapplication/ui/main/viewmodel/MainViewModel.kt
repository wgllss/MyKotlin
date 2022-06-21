package com.example.myapplication.ui.main.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.base.viewmodel.flowOnIOAndcatch
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ui.main.repository.BaiduRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.*

open class MainViewModel() : BaseViewModel() {
    val listData by lazy { MutableLiveData<ArrayList<BaiduDataBean>>() }
    val liveDataPn by lazy { MutableLiveData<Int>() }
    val liveDataGsm by lazy { MutableLiveData<String>() }
    val searchContent by lazy { MutableLiveData<String>() }
    private val repository by lazy { BaiduRepository() }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getImageList() {
        launch2().launch {
            flow {
                emit(repository.get899(searchContent.value!!, searchContent.value!!, liveDataPn.value!!, liveDataGsm.value!!))
            }.filter {
                it.data.removeIf { im ->
                    im.middleURL == null
                }
            }.flowOnIOAndcatch(errorMsgLiveData)
                .collect {
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
package com.example.myapplication.ui.main.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.myapplication.base.viewmodel.flowOnIOAndcatch
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ui.main.repository.BaiduRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import android.util.Log

class CustomItemDataSource
constructor(
    var coroutinescope: CoroutineScope,
    private var searchContent: MutableLiveData<String>,
    val errorMsgLiveData: MutableLiveData<String> = MutableLiveData()
) : PageKeyedDataSource<String, BaiduDataBean>() {


    private val repository = BaiduRepository()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, BaiduDataBean>) {
        flowRequest(callback, null, 30, "")
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, BaiduDataBean>) {
//        flowRequest(null, callback, params.requestedLoadSize, params.key)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, BaiduDataBean>) {
        flowRequest(null, callback, params.requestedLoadSize+30, params.key)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun flowRequest(loadinitialcallback: LoadInitialCallback<String, BaiduDataBean>?, callback: LoadCallback<String, BaiduDataBean>?, pn: Int, gsm: String) {
        Log.e(javaClass.simpleName, "flowRequest pn--->$pn --gsm-->$gsm")
        coroutinescope.launch {
            flow {
                emit(repository.get899(searchContent.value!!, searchContent.value!!, pn!!, gsm))
            }.filter {
                it.data.removeIf { im ->
                    im.middleURL == null
                }
            }.flowOnIOAndcatch(errorMsgLiveData)
                .collect {
                    callback?.onResult(it.data, it.gsm)
                    Log.e(javaClass.simpleName, "it.data--->${it.data.size} --gsm-->$gsm")
                    loadinitialcallback?.onResult(it.data, "", it.gsm)
                }
        }
    }
}

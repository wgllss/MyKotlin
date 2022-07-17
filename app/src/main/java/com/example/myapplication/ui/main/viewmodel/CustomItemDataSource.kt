package com.example.myapplication.ui.main.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.myapplication.application.LCApplication
import com.example.myapplication.base.viewmodel.flowOnIOAndcatch
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ui.main.hint.InitializerEntryPoint
import com.scclzkj.api.Api
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomItemDataSource
constructor(
    var coroutinescope: CoroutineScope,
    private var searchContent: MutableLiveData<String>,
    val errorMsgLiveData: MutableLiveData<String> = MutableLiveData()
) : PageKeyedDataSource<String, BaiduDataBean>() {
    val repository by lazy { InitializerEntryPoint.resolve(LCApplication.application).injectAPi() }
    private lateinit var nextPageKey: String
    private var flag = 30

    init {
        Log.e("wg", "repository ${repository.toString()}")
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, BaiduDataBean>) {
        flowRequest(callback, null, flag, "")
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, BaiduDataBean>) {
//        flowRequest(null, callback, params.requestedLoadSize-30, previousPageKey)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, BaiduDataBean>) {
        flowRequest(null, callback, flag + 30, nextPageKey)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun flowRequest(loadinitialcallback: LoadInitialCallback<String, BaiduDataBean>?, callback: LoadCallback<String, BaiduDataBean>?, pn: Int, gsm: String) {
        Log.e(javaClass.simpleName, "flowRequest pn--->$pn --gsm-->$gsm")
        coroutinescope.launch {
            flow {
                emit(repository.get899(searchContent.value!!, searchContent.value!!, pn!!, gsm))
            }.onStart {

            }.filter {
                it.data.removeIf { im ->
                    im.middleURL == null
                }
            }.onEach {
                callback?.onResult(it.data, it.gsm)
                Log.e(javaClass.simpleName, "it.data--->${it.data.size} --gsm-->${it.gsm}")
                loadinitialcallback?.onResult(it.data, "", it.gsm)
                nextPageKey = it.gsm
                flag = pn
            }.flowOnIOAndcatch(errorMsgLiveData)
                .collect()
        }
    }
}

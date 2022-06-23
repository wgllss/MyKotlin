package com.example.myapplication.ui.main.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ui.main.repository.BaiduRepository
import java.util.*

open class MainViewModel() : BaseViewModel() {
    //    val listData by lazy { MutableLiveData<ArrayList<BaiduDataBean>>() }
//    val liveDataPn by lazy { MutableLiveData<Int>() }
//    val liveDataGsm by lazy { MutableLiveData<String>() }
    val searchContent = MutableLiveData<String>()

    //    private val repository by lazy { BaiduRepository() }
//    private val repository = BaiduRepository()
    private lateinit var factory: BaiduDataDourceFactory //= BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)  //= BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)

    lateinit var listData: LiveData<PagedList<BaiduDataBean>> //= LivePagedListBuilder<String, BaiduDataBean>(factory, 30).build()

    fun invalidateDataSource() {
        factory.liveDataSource.value?.invalidate()
    }

    fun initFatory() {
        factory = BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)
        listData = LivePagedListBuilder<String, BaiduDataBean>(factory, 30).build()
    }

//    @RequiresApi(Build.VERSION_CODES.N)
//    fun getImageList() {
//        launch2().launch {
//            flow {
//                emit(repository.get899(searchContent.value!!, searchContent.value!!, liveDataPn.value!!, liveDataGsm.value!!))
//            }.filter {
//                it.data.removeIf { im ->
//                    im.middleURL == null
//                }
//            }.flowOnIOAndcatch(errorMsgLiveData)
//                .collect {
//                    if (liveDataPn.value == 0) listData.value = it?.data
//                    else listData.value = listData.value?.also { ims ->
//                        ims.addAll(it.data)
//                    }
//                    liveDataPn.value = liveDataPn.value?.plus(30)
//                    liveDataGsm.value = it.gsm
//                }
//        }
//    }
}
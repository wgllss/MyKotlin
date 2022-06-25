package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.data.BaiduDataBean

open class MainViewModel() : BaseViewModel() {
    val searchContent by lazy { MutableLiveData<String>() }

    private lateinit var factory: BaiduDataDourceFactory //= BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)  //= BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)

    lateinit var listData: LiveData<PagedList<BaiduDataBean>> //= LivePagedListBuilder<String, BaiduDataBean>(factory, 30).build()

    fun invalidateDataSource() {
        factory.liveDataSource.value?.invalidate()
    }

    fun initFatory() {
        factory = BaiduDataDourceFactory(launch2(), searchContent, errorMsgLiveData)
        listData = LivePagedListBuilder<String, BaiduDataBean>(factory, 30).build()
    }
}
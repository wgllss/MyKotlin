package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ui.main.repository.BaiduRepository
import kotlinx.coroutines.CoroutineScope

class BaiduDataDourceFactory
constructor(
    private var coroutinescope: CoroutineScope,
    private var searchContent: MutableLiveData<String>,
    val errorMsgLiveData: MutableLiveData<String>
) : DataSource.Factory<String, BaiduDataBean>() {
    val liveDataSource = MutableLiveData<CustomItemDataSource>()


    override fun create(): DataSource<String, BaiduDataBean> {
        val dataSource = CustomItemDataSource(coroutinescope, searchContent, errorMsgLiveData)
        liveDataSource.postValue(dataSource)
        return dataSource
    }
}
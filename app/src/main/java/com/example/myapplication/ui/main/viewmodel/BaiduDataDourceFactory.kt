package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.ex.logE
import kotlinx.coroutines.CoroutineScope

class BaiduDataDourceFactory
constructor(
    private var coroutinescope: CoroutineScope,
    private var searchContent: MutableLiveData<String>,
    val errorMsgLiveData: MutableLiveData<String>
) : DataSource.Factory<String, BaiduDataBean>() {
    //    val liveDataSource = MutableLiveData<CustomItemDataSource>()
    lateinit var dataSource: DataSource<String, BaiduDataBean>

    override fun create(): DataSource<String, BaiduDataBean> {

        logE("BaiduDataDourceFactory create ")


        dataSource = CustomItemDataSource(coroutinescope, searchContent, errorMsgLiveData)
//        liveDataSource.postValue(dataSource)
        return dataSource
    }
}
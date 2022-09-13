package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TestInflaterViewModel : BaseViewModel() {
    val liveDatalist by lazy { MutableLiveData<MutableList<String>>() }
    override fun start() {
        viewModelScope.launch {
            flow {
                val list = mutableListOf<String>()
                for (i in 0..69) {
                    list.add("")
                }
                emit(list)
            }.flowOn(Dispatchers.IO)
                .onEach {
                    liveDatalist.postValue(it)
                }.collect()
        }
    }
}
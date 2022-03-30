package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.viewmodel.BaseViewModel

class ShowImageViewModel:BaseViewModel() {

    var imgList = MutableLiveData<ArrayList<String>>()
    var defaultPosition  = MutableLiveData<Int>()

    init{
        defaultPosition.value=0
    }
}
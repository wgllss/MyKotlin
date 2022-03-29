package com.example.myapplication.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.viewmodel.BaseViewModel

class HomeViewModel :BaseViewModel() {

    var searchContent: MutableLiveData<String> = MutableLiveData()
}
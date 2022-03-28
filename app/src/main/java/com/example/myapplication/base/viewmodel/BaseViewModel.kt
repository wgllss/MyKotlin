package com.example.myapplication.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.DialogBean

open class BaseViewModel : ViewModel() {
    val showDialog: MutableLiveData<DialogBean> = MutableLiveData()
    val errorMsgLiveData:MutableLiveData<String> = MutableLiveData()
}
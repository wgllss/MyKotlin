package com.example.myapplication.ui.main.viewmodel

import com.example.myapplication.application.LCApplication
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.ui.main.dagger2.AppComponent


val BaseViewModel.appComponent: AppComponent by lazy {
    LCApplication.application.appComponent
}
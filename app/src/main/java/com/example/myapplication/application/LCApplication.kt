package com.example.myapplication.application

import android.app.Application
import com.common.framework.utils.AppBuildConfig
import com.example.myapplication.BuildConfig
import com.example.myapplication.net.Config
import com.example.myapplication.net.RetrofitClient
import okhttp3.logging.HttpLoggingInterceptor

class LCApplication : Application() {

    companion object {
        lateinit var application: Application;
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        AppBuildConfig.APPLICATION = LCApplication::class.java.getName()
//        RetrofitClient.DEFAULT_HOST = Config.URL
//        RetrofitClient.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

    }
}
package com.example.myapplication.ui.main.dagger2

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private var application: Application) {

    private val url = "https://image.baidu.com/"

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application

}
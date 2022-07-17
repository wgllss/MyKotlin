package com.example.myapplication.ui.main.hint

import android.app.Application
import android.content.Context
import com.example.myapplication.net.RetrofitClient
import com.scclzkj.api.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class HintModule {
    private val url = "https://image.baidu.com/"
    private val timeout: Long = 30000L

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext application:Context): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(RetrofitClient.HeaderInterceptor())
            .addInterceptor(logging)
            .addInterceptor(RetrofitClient.BaseUrlInterceptor())
            .callTimeout(timeout, TimeUnit.MILLISECONDS)
            //设置连接超时
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            //设置从主机读信息超时
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            //设置写信息超时
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
            .cache(Cache(application.cacheDir, 50 * 1024 * 1024)) //10M cache
            .build();
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()
    }

    @Provides
    @Singleton
    fun providerApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}
package com.example.myapplication.ui.main.dagger2

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private var application: Application) {

//    private val url = "https://image.baidu.com/"
    private val timeout: Long = 30000L

    @Singleton
    @Provides
    fun provideApplicationContext(): Context = application

//    @Provides
//    @Singleton
//    fun provideRetrofit(): Retrofit {
//        val logging = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(RetrofitClient.HeaderInterceptor())
//            .addInterceptor(logging)
//            .addInterceptor(RetrofitClient.BaseUrlInterceptor())
//            .callTimeout(timeout, TimeUnit.MILLISECONDS)
//            //设置连接超时
//            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
//            //设置从主机读信息超时
//            .readTimeout(timeout, TimeUnit.MILLISECONDS)
//            //设置写信息超时
//            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
//            .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
//            .cache(Cache(application.getCacheDir(), 50 * 1024 * 1024)) //10M cache
//            .build();
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .baseUrl(url)
//            .build()
//    }

}
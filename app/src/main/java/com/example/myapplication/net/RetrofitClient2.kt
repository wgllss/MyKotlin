package com.example.myapplication.net
//
//import android.os.Build
//import android.text.TextUtils
//import com.example.myapplication.application.LCApplication
//import okhttp3.*
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
//import java.io.IOException
//import java.util.concurrent.TimeUnit
//
//class RetrofitClient2 private constructor(baseUrl: String) {
//    private val TAG = RetrofitClient2::class.java.simpleName
//    private val url = baseUrl
//    private var TIMEOUT = 30000L
//    private var retrofit: Retrofit
//
//    companion object {
//        @Volatile
//        var instance: RetrofitClient2? = null
//        fun getInstance(baseUrl: String): RetrofitClient2 {
//            if (instance == null) {
//                synchronized(RetrofitClient2::class) {
//                    if (instance == null) {
//                        instance = RetrofitClient2(baseUrl)
//                    }
//                }
//            }
//            return instance!!
//        }
//    }
//
//    init {
//        val logging = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(HeaderInterceptor())
//            .addInterceptor(logging)
//            .addInterceptor(BaseUrlInterceptor())
//            .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//            //设置连接超时
//            .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//            //设置从主机读信息超时
//            .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//            //设置写信息超时
//            .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
//            .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
//            .cache(Cache(LCApplication.application.getCacheDir(), 10 * 1024 * 1024)) //10M cache
//            .build();
//
//        // retrofit创建
//        retrofit = Retrofit.Builder()
//            .client(okHttpClient)
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .baseUrl(url)
//            .build()
//    }
//
//
//
//    private class HeaderInterceptor : Interceptor {
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val request: Request = chain.request()
//            val authorised = request
//                .newBuilder()
//                .addHeader("Connection", "keep-alive") //
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36") //
//                .addHeader("Accept-Language", "zh-CN,zh;q=0.9") //
//                .addHeader("Upgrade-insecure-Requests", "1") //
//                //                    .addHeader("X-ZZ-Timestamp", timestamp)
//                .addHeader("X-ZZ-Device-Sn", Build.SERIAL)
////                .addHeader("v", PackageUtil.getVersionCode(AppBuildConfig.getApplication()).toString() + "")
////                .addHeader("POS-Authorization", PreferenceUtil.getInstance().getToken())
//                .build()
//            return chain.proceed(authorised)
//        }
//    }
//
//
//    private class BaseUrlInterceptor : Interceptor {
//        @Volatile
//        var host: String? = null
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
//            var originalRequest: Request = chain.request()
//            if (!TextUtils.isEmpty(host)) {
//                val newUrl = originalRequest.url.newBuilder()
//                    .host(host!!)
//                    .build()
//                originalRequest = originalRequest.newBuilder()
//                    .url(newUrl)
//                    .build()
//            }
//            return chain.proceed(originalRequest)
//        }
//    }
//
//    fun <T> create(service: Class<T>): T = retrofit.create(service)
//
//}
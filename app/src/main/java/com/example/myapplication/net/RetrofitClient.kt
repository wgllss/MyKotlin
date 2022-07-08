package com.example.myapplication.net

import android.text.TextUtils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import java.io.IOException

object RetrofitClient {
//    private val TIMEOUT = 60000
//
//    //    var DEFAULT_HOST =  "https://3g.163.com/"
//    var DEFAULT_HOST = "https://image.baidu.com/"
//    var level: HttpLoggingInterceptor.Level? = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
//    private val headerInterceptor = HeaderInterceptor()
//    val retrofit = Retrofit.Builder()
//        .baseUrl(DEFAULT_HOST)
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .client(getOkhttp())
//        .build()
//
//    val articleService = retrofit.create(RestService::class.java)
//
//    fun getOkhttp(): OkHttpClient {
//        if (level == HttpLoggingInterceptor.Level.NONE) {
//            return OkHttpClient.Builder()
//                .addInterceptor(headerInterceptor) //添加拦截器
//                //                  .addInterceptor(logging)//添加打印日志
//                .addInterceptor(BaseUrlInterceptor())
//                .callTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置连接超时
//                .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置从主机读信息超时
//                .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置写信息超时
//                .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
////                    .cache(okhttp3.Cache(AppBuildConfig.getApplication().cacheDir, 10 * 1024 * 1024)) //10M cache
//                .build()
//        }
//        val logging = HttpLoggingInterceptor()
//        logging.level = level!!
//        return OkHttpClient.Builder()
//            .addInterceptor(headerInterceptor) //添加拦截器
//            .addInterceptor(logging) //添加打印日志
//            .addInterceptor(BaseUrlInterceptor())
//            //设置连接超时
//            .callTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//            .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置从主机读信息超时
//            .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置写信息超时
//            .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//            .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
////                .cache(okhttp3.Cache(AppBuildConfig.getApplication().cacheDir, 10 * 1024 * 1024)) //10M cache
//            .build()
//    }


    class HeaderInterceptor : okhttp3.Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request: okhttp3.Request = chain.request()
            val authorised: okhttp3.Request = request
                .newBuilder()
                .addHeader("Connection", "keep-alive") //
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36") //
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9") //
                .addHeader("Upgrade-insecure-Requests", "1") //
//                .addHeader("Cookie", "BIDUPSID=0939E0C572A9F7B865E1F5E0554E6D18; PSTM=1637380599; __yjs_duid=1_8437eab0557dbe6a1ceb275834524f481637475509307; indexPageSugList=[\"黄美姬\",\"绿色桌面护眼1920X1080\",\"桌面壁纸\",\"桌面\",\"绿色背景图片\"]; MCITY=-307:301:75:; BDUSS_BFESS=UFhTGRRUExUZm1Oak1ncmE3dFNZMm5oeTFMM0psUUUxaGpWQnYzM0lZV2FqMk5pSUFBQUFBJCQAAAAAAAAAAAEAAAC344NTQXRhcjg4ODgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJoCPGKaAjxia; BAIDUID=B48ED4CBE31306C6DB06B55B9C243470:FG=1; BAIDUID_BFESS=B48ED4CBE31306C6DB06B55B9C243470:FG=1; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDRCVFR[-pGxjrCMryR]=mk3SLVN4HKm") //
                // .addHeader("Connection", "close")
                // .addHeader("X-ZZ-Timestamp", timestamp)
//                .addHeader("X-ZZ-Device-Sn", Build.SERIAL)
//                .addHeader("v", PackageUtil.getVersionCode(AppBuildConfig.getApplication()).toString() + "")
//                .addHeader("POS-Authorization", PreferenceUtil.getInstance().token)
                .build()
            return chain.proceed(authorised)
        }
    }


    class BaseUrlInterceptor : okhttp3.Interceptor {
        @Volatile
        var host: String? = null

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var originalRequest: okhttp3.Request = chain.request()
            if (!TextUtils.isEmpty(host)) {
                val newUrl: HttpUrl = originalRequest.url.newBuilder()
                    .host(host!!)
                    .build()
                originalRequest = originalRequest.newBuilder()
                    .url(newUrl)
                    .build()
            }
            return chain.proceed(originalRequest)
        }
    }
}
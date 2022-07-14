package com.example.myapplication.net
//
//import android.os.Build
//import android.text.TextUtils
//import com.common.framework.network.HttpConfig
//import com.common.framework.utils.AppBuildConfig
//import com.common.framework.utils.PackageUtil
//import okhttp3.HttpUrl
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import okhttp3.logging.HttpLoggingInterceptor.Level
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
//import java.io.IOException
//import java.util.concurrent.TimeUnit
//
//
//class RetrofitWrap private constructor(){
//
//    private val TIMEOUT = HttpConfig.DEFAULT_TIMEOUT_MILLISECONDS
//
//
//
//
//
//    private val headerInterceptor = HeaderInterceptor()
//
//    companion object{
//        var DEFAULT_HOST = ""
//        var level: Level? = null
//         val retrofitWrap:RetrofitWrap by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
//             initRetrofit()
//         }
//
//         fun initRetrofit(): Retrofit {
//            return Retrofit.Builder()
//                .baseUrl(DEFAULT_HOST)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(build())
//                .build()
//        }
//
//        fun build(): OkHttpClient? {
//            if (level == okhttp3.logging.HttpLoggingInterceptor.Level.NONE) {
//                return OkHttpClient.Builder()
//                    .addInterceptor(headerInterceptor) //添加拦截器
//                    //                  .addInterceptor(logging)//添加打印日志
//                    .addInterceptor(BaseUrlInterceptor())
//                    .callTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置连接超时
//                    .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置从主机读信息超时
//                    .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置写信息超时
//                    .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//                    .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
//                    .cache(okhttp3.Cache(AppBuildConfig.getApplication().cacheDir, 10 * 1024 * 1024)) //10M cache
//                    .build()
//            }
//            val logging = HttpLoggingInterceptor()
//            logging.level=level!!
//            return OkHttpClient.Builder()
//                .addInterceptor(headerInterceptor) //添加拦截器
//                .addInterceptor(logging) //添加打印日志
//                //                .addInterceptor(new BaseUrlInterceptor())
//                //设置连接超时
//                .callTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//                .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置从主机读信息超时
//                .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS) //设置写信息超时
//                .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
//                .retryOnConnectionFailure(true) //设置出现错误进行重新连接。
//                .cache(okhttp3.Cache(AppBuildConfig.getApplication().cacheDir, 10 * 1024 * 1024)) //10M cache
//                .build()
//        }
//    }
//
//    private class HeaderInterceptor : okhttp3.Interceptor {
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            val request: okhttp3.Request = chain.request()
//            val authorised: okhttp3.Request = request
//                .newBuilder()
//                .addHeader("Connection", "keep-alive") //
//                // .addHeader("Connection", "close")
//                // .addHeader("X-ZZ-Timestamp", timestamp)
//                .addHeader("X-ZZ-Device-Sn", Build.SERIAL)
//                .addHeader("v", PackageUtil.getVersionCode(AppBuildConfig.getApplication()).toString() + "")
////                .addHeader("POS-Authorization", PreferenceUtil.getInstance().token)
//                .build()
//            return chain.proceed(authorised)
//        }
//    }
//
//
//    private class BaseUrlInterceptor : okhttp3.Interceptor {
//        @Volatile
//        var host: String? = null
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//            var originalRequest: okhttp3.Request = chain.request()
//            if (!TextUtils.isEmpty(host)) {
//                val newUrl: HttpUrl = originalRequest.url.newBuilder()
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
//
//    fun <T> create(service: Class<T>?): T? {
//        return retrofit?.let { it.create(service)}
//    }
//}
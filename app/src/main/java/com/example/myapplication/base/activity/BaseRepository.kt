package com.example.myapplication.base.activity

import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.RetrofitClient2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.ParameterizedType

open class BaseRepository<T>(val defaultHosst: String) {
    open val service by lazy { createService() }

    fun createService(): T {
        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return RetrofitClient2.getInstance(defaultHosst).create(clazz)
    }

    suspend fun <T> apiCall(api: suspend () -> BaseResponse<T>): BaseResponse<T> =
        withContext(Dispatchers.IO) { api.invoke() }

//    suspend fun log() {
//        Log.e(javaClass.simpleName, "线程-4-->${Thread.currentThread().name}")
//    }
//
//    suspend fun <T> callService(request: suspend () -> BaseResponse<T>): Flow<BaseResponse<T>> {
//        return flow {
//            val response = request.invoke()
//            log()
//            emit(response)
//        }.flowOn(Dispatchers.IO)
//
//            .onEach {
//            }
//            .catch { e: Throwable ->
//                when (e) {
//
//                }
//            }
//    }
}
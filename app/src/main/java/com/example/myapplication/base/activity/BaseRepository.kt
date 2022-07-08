package com.example.myapplication.base.activity

import com.example.myapplication.data.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType

open class BaseRepository<T> (private var retrofit: Retrofit){
    open val service by lazy { createService() }

    private fun createService(): T {
        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return retrofit.create(clazz)
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
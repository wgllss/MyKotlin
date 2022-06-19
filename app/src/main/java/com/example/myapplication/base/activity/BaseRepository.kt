package com.example.myapplication.base.activity

import android.util.Log
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.RetrofitClient2
import com.example.myapplication.net.impl.RestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.util.ArrayList

open class BaseRepository(val defaultHosst: String) {

    fun <T> createService(clazz: Class<T>): T = RetrofitClient2.getInstance(defaultHosst).create(clazz)

    suspend fun <T> apiCall(api: suspend () -> BaseResponse<T>): BaseResponse<T> =
        withContext(Dispatchers.IO) { api.invoke() }

    suspend fun log() {
        Log.e(javaClass.simpleName, "线程-4-->${Thread.currentThread().name}")
    }

    suspend fun <T> callService(request: suspend () -> BaseResponse<T>): Flow<BaseResponse<T>> {
        return flow {
            val response = request.invoke()
            log()
            emit(response)
        }.flowOn(Dispatchers.IO)

            .onEach {
            }
            .catch { e: Throwable ->
                when (e) {

                }
            }
    }
}
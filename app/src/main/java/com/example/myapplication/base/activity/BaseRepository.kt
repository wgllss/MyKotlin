package com.example.myapplication.base.activity

import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.ParameterizedType
import android.util.Log

open class BaseRepository<T>() {
    val service by lazy { createService() }

    private fun createService(): T {
        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return RetrofitClient.create(clazz)
    }

    suspend fun <T> apiCall(api: suspend () -> BaseResponse<T>): BaseResponse<T> =
        withContext(Dispatchers.IO) { api.invoke() }
}
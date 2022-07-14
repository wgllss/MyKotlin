package com.example.myapplication.base.activity

import com.example.myapplication.application.LCApplication
import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.ParameterizedType

open class BaseRepository<T>() {
    val service by lazy { createService() }

    private fun createService(): T {
        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
        return RetrofitClient.getInstance(LCApplication.application).create(clazz)
    }

    suspend fun <T> apiCall(api: suspend () -> BaseResponse<T>): BaseResponse<T> =
        withContext(Dispatchers.IO) { api.invoke() }
}
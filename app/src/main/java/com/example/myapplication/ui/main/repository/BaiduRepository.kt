package com.example.myapplication.ui.main.repository

import com.example.myapplication.base.activity.BaseRepository
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.impl.RestService
import kotlinx.coroutines.flow.Flow

class BaiduRepository : BaseRepository("https://image.baidu.com/") {

    private val service by lazy { createService(RestService::class.java) }
    suspend fun get9(word: String, queryWord: String, pn: Int, gsm: String): Flow<BaseResponse<ArrayList<BaiduDataBean>>> {
      return  callService{service.get89(word, queryWord, pn, gsm)}

//        return service.get9(word, queryWord, pn, gsm)
    }

    suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String) : BaseResponse<ArrayList<BaiduDataBean>>{
        return service.get899(word, queryWord, pn, gsm)
    }


//    @RequiresApi(Build.VERSION_CODES.N)
//    suspend fun get89(word: String, queryWord: String, pn: Int, gsm: String) = flow {
//        emit(service.get89(word, queryWord, pn, gsm))
//    }.filter {
//        Log.e(javaClass.simpleName, "线程-4-->${Thread.currentThread().name}")
//        it.data.removeIf { im ->
//            im.middleURL == null
//        }
//    }.flowOn(Dispatchers.IO).onEach {
//        Log.e(javaClass.simpleName, "线程-5-->${Thread.currentThread().name}")
//    }.catch { e: Throwable ->
//        e.printStackTrace()
//    }
//
//    suspend fun get8(): String {
//        return service.get8()
//    }
//
//    suspend fun get7(): BaiDuImageResponse {
//        return service.get7()
//    }
//
//    fun get5(): Call<String> {
//        return service.get5()
//    }
//
//    fun get2(url: String): Flowable<BaiDuImageResponse> {
//        return service.get2(url)
//    }
}
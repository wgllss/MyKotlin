package com.example.myapplication

import com.example.myapplication.base.activity.BaseRepository
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import com.scclzkj.api.Api
import retrofit2.Retrofit
import javax.inject.Inject

//class RestServiceImpl @Inject constructor(retrofit: Retrofit) : BaseRepository<Api>(retrofit), Api {
//
//    override suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String): BaseResponse<ArrayList<BaiduDataBean>> {
//        return service.get899(word, queryWord, pn, gsm);
//    }
//
//}
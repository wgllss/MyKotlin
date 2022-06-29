package com.example.myapplication

import com.example.myapplication.base.activity.BaseRepository
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.impl.RestService
import javax.inject.Inject

class RestServiceImpl @Inject constructor() : BaseRepository<RestService>(), RestService {

    override suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String): BaseResponse<ArrayList<BaiduDataBean>> {
        return service.get899(word, queryWord, pn, gsm);
    }
}
package com.example.myapplication.ui.main.repository

import com.example.myapplication.base.activity.BaseRepository
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import com.example.myapplication.net.impl.RestService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BaiduRepository @Inject constructor() : BaseRepository<RestService>("https://image.baidu.com/"), RestService {

    override suspend fun get899(word: String, queryWord: String, pn: Int, gsm: String): BaseResponse<ArrayList<BaiduDataBean>> {
        return service.get899(word, queryWord, pn, gsm)
    }
}
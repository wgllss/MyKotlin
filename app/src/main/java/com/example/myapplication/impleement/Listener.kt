package com.example.myapplication.impleement

import com.atar.annotations.AutoCreateService
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import retrofit2.http.Query

interface Listener {

    @AutoCreateService
    fun run(@Query("word") word: String, @Query("queryWord") queryWord: String, @Query("pn") pn: Int, @Query("gsm") gsm: String): BaseResponse<ArrayList<BaiduDataBean>>;

}
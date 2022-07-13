package com.scclzkj.api

import com.atar.annotations.CreateService
import retrofit2.http.GET
import retrofit2.http.Query

@CreateService(interfaceApi = Api::class, superClass = "com.example.myapplication.base.activity.BaseRepository")
interface Api {

    @GET("search/acjson?tn=resultjson_com&logid=12307192414549550342&ipn=rj&ct=201326592&is=&fp=result&fr=&cg=star&rn=30")
    suspend fun get899(@Query("word") word: String, @Query("queryWord") queryWord: String, @Query("pn") pn: Int, @Query("gsm") gsm: String): com.scclzkj.api.data.BaseResponse<ArrayList<com.scclzkj.api.data.BaiduDataBean>>
}
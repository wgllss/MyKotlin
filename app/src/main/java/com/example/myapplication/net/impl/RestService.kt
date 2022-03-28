package com.example.myapplication.net.impl

import com.example.myapplication.data.BaiDuImageResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.*

interface RestService {

    @GET("search/acjson?tn=resultjson_com&logid=12307192414549550342&ipn=rj&ct=201326592&is=&fp=result&fr=&cg=star&rn=30")
    suspend fun get9(@Query("word") word: String, @Query("queryWord") queryWord: String,@Query("pn") pn:Int,@Query("gsm") gsm:String): BaiDuImageResponse

    @GET("touch/nc/api/user/recommend/GuessLike/3-0-10-40.do?offset=1&size=20")
    suspend fun get8(): String

    @GET("search/acjson?tn=resultjson_com&logid=12307192414549550342&ipn=rj&ct=201326592&is=&fp=result&fr=&word=黄美姬&cg=star&queryWord=黄美姬")
    suspend fun get7(): BaiDuImageResponse

    @GET("search/acjson?tn=resultjson_com&logid=12307192414549550342&ipn=rj&ct=201326592&is=&fp=result&fr=&word=黄美姬&cg=star&queryWord=黄美姬")
    fun get5(): Call<String>

    @GET
    fun get2(@Url url: String): Flowable<BaiDuImageResponse>

}
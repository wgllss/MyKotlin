package com.scclzkj.api

import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.data.BaseResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("search/acjson?tn=resultjson_com&logid=12307192414549550342&ipn=rj&ct=201326592&is=&fp=result&fr=&cg=star&rn=30")
    suspend fun get899(@Query("word") word: String, @Query("queryWord") queryWord: String, @Query("pn") pn: Int, @Query("gsm") gsm: String): BaseResponse<MutableList<BaiduDataBean>>

    @POST("http://clz.aiddys.cn:9999/pay-c/order/pay/prePay")
    suspend fun prePay(@Body body: RequestBody): String


    @GET("https://www.hifini.com/get_music.php?key=f64jyoHzZz8hxlhYVa0CUD9XmZq926FjKz9fVELdF299ItK547n3PLnwrTKamHGx")
    suspend fun getMusic(): Response<ResponseBody>

//    @GET("https://gitee.com/wgllss888/wgllss-music-data-source/raw/master/jar/classes3_dex.jar")
    @GET("https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/purple/download_skin.apk")
//    @GET("http://192.168.3.21:8080/assets/music_plugin/classes3_dex.jar")
    suspend fun getLength(): Response<ResponseBody>
}
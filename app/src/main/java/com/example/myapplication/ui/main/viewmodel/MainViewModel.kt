package com.example.myapplication.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.net.RetrofitClient
import kotlinx.coroutines.launch
import java.util.*

open class MainViewModel() : BaseViewModel() {
//    var uidata: MutableLiveData<String> = MutableLiveData()
    var listData= MutableLiveData<ArrayList<BaiduDataBean>>()
    var liveDataPn = MutableLiveData<Int>()
    var liveDataGsm = MutableLiveData<String>()
    var searchContent= MutableLiveData<String> ()

//    var listdata = lazy {
//        MutableLiveData<List<BaiduDataBean>>()
//    }

    init {
        liveDataPn.value=0
        liveDataGsm.value=""

    }

    fun getImageList(){
        viewModelScope.launch {

            var baiduimageresponse = RetrofitClient.articleService.get9(searchContent.value!!,searchContent.value!!,liveDataPn.value!!,liveDataGsm.value!!)

            for (i in baiduimageresponse.data!!.indices){
                if(baiduimageresponse.data?.get(i)!=null&&baiduimageresponse.data?.get(i)?.middleURL==null){
                    baiduimageresponse.data?.removeAt(i)
                }
            }
            if(liveDataPn.value==0){
               listData.value=baiduimageresponse?.data
           }else{
               var list  = listData.value
               list?.addAll(baiduimageresponse?.data!!)
               listData.value=list!!
           }
            Log.e("Wg"," listData.value.size-->${ listData?.value?.size}")
            liveDataPn.value=liveDataPn.value!!+30
            liveDataGsm.value=baiduimageresponse.gsm
        }
//        RetrofitClient.articleService.get5().enqueue(object :Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                Log.e("wg--",response.body().toString())
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable){
//            }
//        })
    }
}
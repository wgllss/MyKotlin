package com.example.myapplication.ui.main.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseActivity
import com.example.myapplication.ex.logE
import com.example.myapplication.net.impl.RequestBodyWrapper
import com.scclzkj.api.Api
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import javax.inject.Inject

@AndroidEntryPoint
class TestNetActivity : BaseActivity(), View.OnClickListener {

    @Inject
    lateinit var api: Api

    override fun initControl(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_text_net)
    }

    override fun bindEvent() {

    }

    override fun initValue() {

    }

    override fun onClick(v: View?) {
        lifecycleScope.launch {
            try{
                val body = "{\n" +
                        "    \"unifiedPayNo\": \"P202210130915000001\",\n" +
                        "    \"payMethod\": 2,\n" +
                        "    \"ownerId\": 21,\n" +
                        "    \"orderType\": 2,\n" +
                        "    \"authCode\": \"\"\n" +
                        "}"
                val requestBody: RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(),body)
                val result = api.prePay(requestBody)
                logE("result---> ${result}")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
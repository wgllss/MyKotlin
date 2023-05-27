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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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

    fun onClick3(v: View?) {
//        val map = mutableMapOf<String, Flow<Any>>()
        var flowZip: Flow<Any>? = null
        var flow: Flow<Int>? = null
        val flow1 = flow { emit(1) }
        val flow2 = flow { emit(2) }
        val flow3 = flow { emit(3) }
        val flow4 = flow { emit(4) }
        val flow5 = flow { emit(5) }
        val flow6 = flow { emit(6) }
        val flow7 = flow { emit(7) }
        val flow8 = flow { emit(8) }

        val flows = mutableMapOf<String, Flow<Int>>(
            "flowkey1" to flow1,
            "flowkey2" to flow2,
            "flowkey3" to flow3,
            "flowkey4" to flow4,
            "flowkey5" to flow5,
            "flowkey6" to flow6,
            "flowkey7" to flow7,
            "flowkey8" to flow8
        )

        flows.forEach {
            if (flow == null) {
                flow = it.value
            } else {
                if (flowZip == null) {
                    flowZip = flow!!.zip(it.value) { it1, it2 ->
                        logE("打印 it1: $it1   it2:$it2")
                    }
                } else {
                    flowZip = flowZip!!.zip(it.value) { _, it2 ->
                        logE("打印    it2:$it2")
                    }
                }
            }
        }
        lifecycleScope.launch {
            flowZip!!.catch { it.printStackTrace() }
                .flowOn(Dispatchers.IO)
                .collect()
        }
    }

    fun onClick1(v: View?) {
        lifecycleScope.launch {
            val result = api.getMusic()

            logE("result---> ${result.raw().request.url}")
        }
    }

    override fun onClick(v: View?) {
        lifecycleScope.launch {
            try {
                val body = "{\n" +
                        "    \"unifiedPayNo\": \"P202210130915000001\",\n" +
                        "    \"payMethod\": 2,\n" +
                        "    \"ownerId\": 21,\n" +
                        "    \"orderType\": 2,\n" +
                        "    \"authCode\": \"\"\n" +
                        "}"
//                val requestBody: RequestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), body)
//                val result = api.prePay(requestBody)
                val result = api.getLength()



                logE("result---> ${result}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
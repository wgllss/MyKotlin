package com.example.myapplication.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.adapter.BaseDataBindingAdapter
import com.example.myapplication.databinding.AdapterItemRecognizeResultBinding
import com.example.myapplication.ex.loadUrl
import javax.inject.Inject

class RecognizeResultAdapter @Inject constructor(): BaseDataBindingAdapter<String, AdapterItemRecognizeResultBinding>() {
    override fun onBindItem(binding: AdapterItemRecognizeResultBinding, item: String, holder: RecyclerView.ViewHolder, position: Int) {
        binding.apply {
            imgGoods.loadUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage.zhms.cn%2F2020-06%2F6375460c0deb4093840a6114d5b9ea01.jpg%3Fx-oss-process%3Dimage%2Fformat%2Cjpg%2Finterlace%2C1%2Fwatermark%2Cimage_RGVmYXVsdC9iLnBuZw%3D%3D%2Ct_35%2Cg_se%2Cx_10%2Cy_10&refer=http%3A%2F%2Fimage.zhms.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1659840766&t=e16d3c38be983cf210dda5a82090532f")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun getLayoutResId(viewType: Int)= R.layout.adapter_item_recognize_result
}
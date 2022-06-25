package com.example.myapplication.ui.main.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.adapter.BasePagedListAdapter
import com.example.myapplication.base.viewmodel.loadUrl
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.databinding.ItemImageBinding


class ImageAdapter : BasePagedListAdapter<BaiduDataBean, ItemImageBinding>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BaiduDataBean>() {
            override fun areItemsTheSame(oldItem: BaiduDataBean, newItem: BaiduDataBean): Boolean =
                oldItem == newItem
            override fun areContentsTheSame(oldItem: BaiduDataBean, newItem: BaiduDataBean): Boolean =
                oldItem.middleURL == newItem.middleURL
        }
    }

    override fun getLayoutResId(viewType: Int): Int = R.layout.item_image

    override fun onBindItem(binding: ItemImageBinding, item: BaiduDataBean?, holder: RecyclerView.ViewHolder, position: Int) {
       binding.image?.apply {
           item?.middleURL?.let { loadUrl(it) }
       }
    }
}




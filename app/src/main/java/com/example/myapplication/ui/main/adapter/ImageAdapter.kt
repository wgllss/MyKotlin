package com.example.myapplication.ui.main.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.base.adapter.BaseDataBindingAdapter
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.databinding.ItemImageBinding


class ImageAdapter(context: Context) : BaseDataBindingAdapter<BaiduDataBean, ItemImageBinding>(context) {
    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.item_image;
    }

    override fun onBindItem(binding: ItemImageBinding?, item: BaiduDataBean?, holder: RecyclerView.ViewHolder?) {
        binding?.item=item
        binding?.executePendingBindings()
        binding?.image?.let {
            Glide.with(mContext)
                .load(item?.middleURL)
                .into(it)
        }
    }
}
package com.example.myapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.viewmodel.loadUrl
import com.example.myapplication.data.BaiduDataBean
import com.example.myapplication.databinding.ItemImageBinding


class ImageAdapter : PagedListAdapter<BaiduDataBean, ImageAdapter.MViewHolder>(diffCallback) {


//    override fun getLayoutResId(viewType: Int): Int {
//        return R.layout.item_image;
//    }
//
//    override fun onBindItem(binding: ItemImageBinding?, item: BaiduDataBean?, holder: RecyclerView.ViewHolder?) {
//        binding?.item=item
//        binding?.executePendingBindings()
//        binding?.image?.let {
//            Glide.with(mContext)
//                .load(item?.middleURL)
//                .into(it)
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),  R.layout.item_image, parent, false) as ItemImageBinding;
        val holder =  MViewHolder(binding.getRoot());
        return holder;
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
         DataBindingUtil.getBinding<ItemImageBinding>(holder.itemView)?.let {
             it.item= getItem(position)
             it.executePendingBindings()
             it.item?.middleURL?.let { it1 -> it.image?.loadUrl(it1) }
         }
//        binding.executePendingBindings()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<BaiduDataBean>() {
            override fun areItemsTheSame(oldItem: BaiduDataBean, newItem: BaiduDataBean): Boolean =
                oldItem == newItem


            override fun areContentsTheSame(oldItem: BaiduDataBean, newItem: BaiduDataBean): Boolean =
                oldItem.middleURL == newItem.middleURL

        }

    }

    inner class MViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}




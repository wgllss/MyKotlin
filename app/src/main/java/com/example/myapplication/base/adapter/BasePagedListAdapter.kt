package com.example.myapplication.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ex.LogTimer

abstract class BasePagedListAdapter<T, VB : ViewDataBinding>(@NonNull diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, BasePagedListAdapter.BaseBindingViewHolder>(diffCallback) {

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        LogTimer.initTime(this)
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), getLayoutResId(viewType), parent, false)
        LogTimer.LogE(this, "onCreateViewHolder")
        return BaseBindingViewHolder(binding?.root)
    }

    override fun onBindViewHolder(holder: BaseBindingViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<VB>(holder.itemView)
        val item = getItem(position)
        onBindItem(binding!!, item, holder, position)
        binding?.apply {
            executePendingBindings()
        }
    }

    protected abstract fun onBindItem(binding: VB, item: T?, holder: RecyclerView.ViewHolder, position: Int)


    class BaseBindingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    }

}
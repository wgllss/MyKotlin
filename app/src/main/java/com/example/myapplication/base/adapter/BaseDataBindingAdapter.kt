package com.example.myapplication.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.ex.LogTimer

abstract class BaseDataBindingAdapter<T, VB : ViewDataBinding> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var mData: MutableList<T>

    fun notifyData(mData: MutableList<T>) {
        if (mData == null) {
            this.mData = mutableListOf()
        } else {
            this.mData = mData
        }
        notifyDataSetChanged()
    }

    fun addMoreList(mData: MutableList<T>) {
        mData?.takeIf { it.isNotEmpty() && it.size > 0 }
            ?.let {
                it.addAll(mData)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseBindingViewHolder {
        LogTimer.initTime(this)
        val binding = DataBindingUtil.inflate<VB>(LayoutInflater.from(parent.context), getLayoutResId(viewType), parent, false)
        LogTimer.LogE(this,"onCreateViewHolder")
        return BaseBindingViewHolder(binding?.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<VB>(holder.itemView)
        val item = getItem(position)
        onBindItem(binding!!, item, holder, position)
        binding.executePendingBindings()
    }

    private fun getItem(position: Int): T = mData[position]


    protected abstract fun onBindItem(binding: VB, item: T, holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemCount(): Int = if (!this::mData.isInitialized) 0 else mData.size

    protected fun getDataSize() = if (!this::mData.isInitialized) 0 else mData.size

    @LayoutRes
    protected abstract fun getLayoutResId(viewType: Int): Int

    class BaseBindingViewHolder internal constructor(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}
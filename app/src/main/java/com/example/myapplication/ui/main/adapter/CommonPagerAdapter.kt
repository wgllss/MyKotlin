package com.example.myapplication.ui.main.adapter

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

open class CommonPagerAdapter<T>(open var list: ArrayList<T>) : PagerAdapter() {

    private var handler: Handler? = null

    constructor(list: ArrayList<T>, handler: Handler?) : this(list) {
        this.handler = handler
    }

    fun clearList() {
        list?.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}
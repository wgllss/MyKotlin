package com.example.myapplication.ui.main.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import uk.co.senab.photoview.PhotoView

class ScanPagerAdapter(override var list: ArrayList<String>) :CommonPagerAdapter<String>(list) {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photoView = PhotoView(container.context)
        if (list != null && list.size > 0) {
            photoView.minimumScale = 0.2f
            val imgUrl = list[position]
            Glide.with(container.context).load(imgUrl).into(photoView)
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
        return photoView
    }
}
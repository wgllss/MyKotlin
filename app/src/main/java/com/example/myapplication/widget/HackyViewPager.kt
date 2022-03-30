package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class HackyViewPager(context: Context, attrs: AttributeSet  ): ViewPager(context ,attrs) {
     var  isLocked:Boolean = false

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return if (!this.isLocked) super.onInterceptTouchEvent(event) else false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (!this.isLocked) super.onTouchEvent(ev) else false
    }

}
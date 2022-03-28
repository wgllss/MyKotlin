package com.example.myapplication.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.common.framework.widget.CommonToast
import com.example.myapplication.base.dialog.CommonLoadingView

open abstract class BaseActivity : AppCompatActivity() {
    var loading: CommonLoadingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState);
    }

    open fun init(savedInstanceState: Bundle?) {
        initControl(savedInstanceState)
        bindEvent()
        initValue()
    }

    open abstract fun initControl(savedInstanceState: Bundle?)
    open abstract fun bindEvent()
    open abstract fun initValue()

    open fun onToast(toastContent: String?) {
        CommonToast.show(toastContent)
    }

    //是否loading
    open fun isShowloading(): Boolean? {
        return loading?.isShowing()
    }

    open fun showloading(showText: String?) {
        if (null == loading) {
            loading = CommonLoadingView(this)
        }
        if (loading!!.isShowing()) {
            return
        }
        loading?.show(showText)
    }

    open fun hideLoading() {
        loading?.dismiss()
        loading = null
    }

    fun setFramgment(fragment: Fragment, layoutID: Int) {
        fragment.userVisibleHint = true;
        supportFragmentManager.beginTransaction().replace(layoutID, fragment).commitAllowingStateLoss()
    }

}
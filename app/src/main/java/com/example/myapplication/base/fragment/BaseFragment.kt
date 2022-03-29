package com.example.myapplication.base.fragment

import androidx.fragment.app.Fragment
import com.example.myapplication.base.activity.BaseActivity
import com.example.myapplication.widget.CommonToast

open class BaseFragment : Fragment() {

    fun onToast(toastContent: String?) {
        CommonToast.show(toastContent)
    }

    fun showloading() {
        showloading("请稍候...")
    }

    //是否loading
    protected fun isShowloading(): Boolean? {
        return if (activity != null && activity is BaseActivity)
            (activity as BaseActivity)?.isShowloading()
        else false
    }

    fun showloading(showText: String?) {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)?.showloading(showText)
        }
    }

    fun hideLoading() {
        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity?)?.hideLoading()
        }
    }

    protected fun setFragment(replaceLayoutID: Int, f: Fragment) {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        f.userVisibleHint = true
        ft.replace(replaceLayoutID, f)
        ft.commitAllowingStateLoss()
    }

    fun isShowFragment(mFragment: Fragment?, isShow: Boolean) {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        if (mFragment != null) {
            if (isShow) {
                ft.show(mFragment)
            } else {
                ft.hide(mFragment)
            }
            ft.commitAllowingStateLoss()
        }
    }

}
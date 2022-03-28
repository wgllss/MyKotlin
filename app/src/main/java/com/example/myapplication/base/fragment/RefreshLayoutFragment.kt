package com.example.myapplication.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.common.framework.common.CommonHandler
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

open abstract class RefreshLayoutFragment<VM : BaseViewModel, DB : ViewDataBinding> :BaseMvvmFragment<VM, DB>() , OnRefreshListener, OnLoadMoreListener {

    protected var refreshLayout: RefreshLayout? = null
    private var isFirstLoad = true

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        initUI(isVisibleToUser)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (refreshLayout != null) {
            refreshLayout!!.setOnRefreshListener(this)
            refreshLayout!!.setOnLoadMoreListener(this)
        }
    }

    protected open fun initUI(isVisibleToUser: Boolean) {
        CommonHandler.getInstatnce().handler.postDelayed({
            if (refreshLayout == null || activity == null || !isVisibleToUser) {
//                    initUI(isVisibleToUser);
            } else {
                if (isFirstLoad) {
                    initValue()
                    isFirstLoad = false
                }
            }
        }, 100)
    }

    open abstract fun initValue()

    open fun setEnableRefresh(enableRefresh: Boolean) {
        if (refreshLayout != null) {
            refreshLayout!!.isEnableRefresh = enableRefresh
        }
    }

    open fun setEnableAutoLoadMore(enableAutoLoadMore: Boolean) {
        if (refreshLayout != null) {
            refreshLayout!!.setEnableAutoLoadMore(enableAutoLoadMore)
        }
    }

    open fun setEnableLoadMore(enableLoadMore: Boolean) {
        if (refreshLayout != null) {
            refreshLayout!!.isEnableLoadMore = enableLoadMore
        }
    }

    open fun autoRefresh() {
        if (refreshLayout != null) {
            refreshLayout!!.autoRefresh()
        }
    }

    open fun autoLoadMore() {
        if (refreshLayout != null) {
            refreshLayout!!.autoLoadMore()
        }
    }

    open fun finishRefresh() {
        if (refreshLayout != null) {
            refreshLayout!!.finishRefresh()
        }
    }

    open fun finishLoadMore() {
        if (refreshLayout != null) {
            refreshLayout!!.finishLoadMore()
        }
    }

    open fun finishBoth() {
        if (refreshLayout != null) {
            refreshLayout!!.finishRefresh()
            refreshLayout!!.finishLoadMore()
        }
    }

    open fun isFirstLoad(): Boolean {
        return isFirstLoad
    }

    open fun setIsFirstLoad(isFirstLoad: Boolean) {
        this.isFirstLoad = isFirstLoad
    }

    protected open fun setRefreshUI(refreshLayout: RefreshLayout?) {
        this.refreshLayout = refreshLayout
        if (refreshLayout != null) {
            setEnableRefresh(true)
            setEnableLoadMore(true)
            setEnableAutoLoadMore(true)
        }
    }

}
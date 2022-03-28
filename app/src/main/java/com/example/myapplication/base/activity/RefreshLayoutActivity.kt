package com.example.myapplication.base.activity

import androidx.databinding.ViewDataBinding
import com.example.myapplication.base.viewmodel.BaseViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

open abstract class RefreshLayoutActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseMvvmActivity<VM, DB>(), OnRefreshListener, OnLoadMoreListener {

    protected var refreshLayout: RefreshLayout? = null
    private var isFirstLoad = true

    override fun bindEvent() {
        super.bindEvent()
        if (refreshLayout != null) {
            refreshLayout!!.setOnRefreshListener(this)
            refreshLayout!!.setOnLoadMoreListener(this)
        }
    }

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
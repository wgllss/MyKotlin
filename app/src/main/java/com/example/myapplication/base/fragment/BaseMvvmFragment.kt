package com.example.myapplication.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.viewmodel.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseMvvmFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment() {
    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        initViewModel()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initObserve()
    }

    private fun initViewModel() {
        if (getModelClass() == null) {
            return
        }
        viewModel = ViewModelProvider(this).get(getModelClass().java)

    }


    protected abstract fun getModelClass(): KClass<VM>

    protected abstract fun getLayoutId(): Int

    /**
     * 监听当前ViewModel中 showDialog和error的值
     */
    private fun initObserve() {
        if (viewModel != null) {
            viewModel.showDialog.observe(this, Observer { it ->
                if (it.isShow) showloading(it.msg) else hideLoading()
            })
            viewModel.errorMsgLiveData.observe(this, Observer {
                onToast(it)
            })
        }
    }
}
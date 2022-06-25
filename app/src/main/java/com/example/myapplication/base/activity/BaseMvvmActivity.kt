package com.example.myapplication.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

open abstract class BaseMvvmActivity< VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity() {

    lateinit var viewModel: VM
    lateinit var binding: DB

    override fun initControl(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutID())
        binding.lifecycleOwner = this
        initViewModel()
    }

    override fun bindEvent() {
        viewModel?.showDialog?.observe(this, Observer {
            if (it.isShow) showloading(it.msg) else hideLoading()
        })
        viewModel.errorMsgLiveData.observe(this, Observer {
            onToast(it)
            hideLoading()
        })
    }

    fun initViewModel() {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        type?.let {
            viewModel = ViewModelProvider(this).get(it)
        }
    }

    protected abstract fun getLayoutID(): Int

}
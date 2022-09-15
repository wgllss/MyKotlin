package com.example.myapplication.base.activity

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

open abstract class BaseMvvmActivity< VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity() {

    protected val viewModel by lazyViewModels()
    lateinit var binding: DB

    override fun initControl(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, getLayoutID())
        binding.lifecycleOwner = this
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

    @MainThread
    inline fun lazyViewModels(): Lazy<VM> {
        val cls = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return ViewModelLazy(cls.kotlin, { viewModelStore }, { defaultViewModelProviderFactory })
    }

    protected abstract fun getLayoutID(): Int

}
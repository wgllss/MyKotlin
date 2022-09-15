package com.example.myapplication.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseMvvmFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment() {
    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            viewModel = lazyViewModels(it).value
            initObserve()
        }
        initObserve()
    }

    /**
     * activity 和 fragmnet 是否公用同一个viewModel
     */
    protected open fun activitySameViewModel() = false

    @MainThread
    private fun lazyViewModels(activity: ComponentActivity): Lazy<VM> {
        val cls = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        return if (activitySameViewModel()) ViewModelLazy(cls.kotlin, { activity.viewModelStore }, { activity.defaultViewModelProviderFactory })
        else ViewModelLazy(cls.kotlin, { viewModelStore }, { defaultViewModelProviderFactory })
    }


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
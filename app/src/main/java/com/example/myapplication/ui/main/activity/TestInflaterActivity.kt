package com.example.myapplication.ui.main.activity

import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseMvvmActivity
import com.example.myapplication.databinding.ActivityTestInflaterBinding
import com.example.myapplication.ui.main.adapter.RecognizeResultAdapter
import com.example.myapplication.ui.main.viewmodel.TestInflaterViewModel
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TestInflaterActivity : BaseMvvmActivity<TestInflaterViewModel, ActivityTestInflaterBinding>() {

    @Inject
    lateinit var mRecognizeresultadapter: Lazy<RecognizeResultAdapter>

    override fun initValue() {
    }

    override fun getLayoutID() = R.layout.activity_test_inflater

    override fun initControl(savedInstanceState: Bundle?) {
        super.initControl(savedInstanceState)
        binding?.apply {
            recognizeresultadapter =mRecognizeresultadapter.get()
            executePendingBindings()
        }
    }

    override fun bindEvent() {
        super.bindEvent()
        viewModel?.liveDatalist?.observe(this) {
            mRecognizeresultadapter.get().notifyData(it)
        }
        viewModel.start()
    }
}
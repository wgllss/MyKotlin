package com.example.myapplication.ui.main.activity

import android.text.TextUtils
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseMvvmActivity
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.ui.main.viewmodel.HomeViewModel

class HomeActivity : BaseMvvmActivity<HomeViewModel, ActivityHomeBinding>(), View.OnClickListener {

    override fun getLayoutID(): Int {
        return R.layout.activity_home
    }

    override fun initValue() {
        binding.homeViewModel = viewModel
        binding.activity = this
        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_search -> {
                var content = viewModel.searchContent.value
                if (TextUtils.isEmpty(content)) {
                    onToast("请输入搜索内容")
                    return
                }
                TestActivity.startTestActivity(this,content)
            }
        }
    }
}
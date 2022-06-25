package com.example.myapplication.ui.main.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.base.activity.BaseMvvmActivity
import com.example.myapplication.databinding.ActivityShowImageBinding
import com.example.myapplication.ui.main.adapter.ScanPagerAdapter
import com.example.myapplication.ui.main.viewmodel.ShowImageViewModel
import com.example.myapplication.widget.HackyViewPager
import kotlinx.android.synthetic.main.activity_show_image.*
import java.util.*
import kotlin.reflect.KClass

class ShowImageActivity : BaseMvvmActivity<ShowImageViewModel, ActivityShowImageBinding>() {

    companion object {
        private val TAG = ShowImageActivity::class.simpleName
        private const val IMAGE_LIST_KEY = "IMAGE_LIST_KEY"
        private const val DEFAULT_POSITION_KEY = "DEFAULT_POSITION_KEY"
        private const val ISLOCKED_ARG = "ISLOCKED_KEY"

        fun startShowImage(context: Context?, imgList: ArrayList<String>?, position: Int) {
            val intent = Intent(context, ShowImageActivity::class.java)
            intent.putStringArrayListExtra(IMAGE_LIST_KEY, imgList)
            intent.putExtra(DEFAULT_POSITION_KEY, position)
            context?.startActivity(intent)
        }
    }

    override fun initValue() {
        Log.e(TAG, "");
        viewModel.imgList.value = intent.getStringArrayListExtra(IMAGE_LIST_KEY)
        viewModel.defaultPosition.value = intent.getIntExtra(DEFAULT_POSITION_KEY, 0)
        var mScanPagerAdapter = ScanPagerAdapter(viewModel?.imgList?.value!!)
        view_pager.setAdapter(mScanPagerAdapter)
        view_pager.setCurrentItem(viewModel.defaultPosition.value!!)
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_show_image
    }

    override fun initControl(savedInstanceState: Bundle?) {
        super.initControl(savedInstanceState)
        if (savedInstanceState != null && view_pager != null) {
            val isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false)
            view_pager.isLocked=isLocked
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (view_pager != null && view_pager is HackyViewPager) {
            outState.putBoolean(ISLOCKED_ARG, view_pager.isLocked)
        }
        super.onSaveInstanceState(outState)
    }
}
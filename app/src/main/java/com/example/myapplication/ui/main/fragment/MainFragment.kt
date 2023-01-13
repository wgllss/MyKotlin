package com.example.myapplication.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.base.fragment.RefreshLayoutFragment
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.main.activity.ShowImageActivity
import com.example.myapplication.ui.main.adapter.ImageAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.widget.OnRecyclerVIewItemClickLIstener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment(private val content: String) : RefreshLayoutFragment<MainViewModel, MainFragmentBinding>() {

    companion object {
        fun newInstance(content: String): MainFragment {
            var instance = MainFragment(content)
            return instance
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefreshUI(binding.refreshLayout)
        binding.refreshLayout.apply {
            setEnableLoadMore(true)
            setEnableAutoLoadMore(true)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.setViewmodel(viewModel)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.setItemViewCacheSize(20)
        binding.recycler.addOnItemTouchListener(object : OnRecyclerVIewItemClickLIstener(binding.recycler) {
            override fun onItemClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
                var imgList = ArrayList<String>()
                imgList.add(viewModel.listData?.value?.get(position)!!.middleURL!!)
                ShowImageActivity.startShowImage(activity, imgList, 0)
            }

            override fun onItemLongClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
//                Log.e("wg", "onItemLongClickListener position = $position");
            }
        })
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                android.util.Log.e("wg", "newState:$newState")
                when (newState) {
                    //滑动停止
                    RecyclerView.SCROLL_STATE_IDLE -> activity?.let {
                        Glide.with(it).resumeRequests()
                    }
                    else -> {
                        activity?.let {
                            Glide.with(it).pauseRequests()
                        }
                    }
                }
            }
        })

        var imageAdapter: ImageAdapter? = activity?.let { ImageAdapter() };
        binding.imageAdapter = imageAdapter;


        binding.executePendingBindings()
    }

    override fun initValue() {
        viewModel.searchContent.value = content
        viewModel.initFatory()
        viewModel.listData.observe(viewLifecycleOwner, Observer {
            binding.imageAdapter?.submitList(it)
        })
//        autoRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.invalidateDataSource()
        finishBoth()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
//        viewModel.getImageList()
        finishBoth()
    }
}
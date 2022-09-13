package com.example.myapplication.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.fragment.RefreshLayoutFragment
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.main.activity.ShowImageActivity
import com.example.myapplication.ui.main.adapter.ImageAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.widget.OnRecyclerVIewItemClickLIstener
import com.scwang.smart.refresh.layout.api.RefreshLayout

class MainFragment(content: String) : RefreshLayoutFragment<MainViewModel, MainFragmentBinding>() {
    var searchContent: String? = null

    companion object {
        fun newInstance(content: String): MainFragment {
            var instance = MainFragment(content)
            instance.searchContent = content
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

        var imageAdapter: ImageAdapter? = activity?.let { ImageAdapter() };
        binding.imageAdapter = imageAdapter;


        binding.executePendingBindings()
    }

    override fun initValue() {
        viewModel.searchContent.value = searchContent
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
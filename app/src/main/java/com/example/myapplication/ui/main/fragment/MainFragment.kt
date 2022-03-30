package com.example.myapplication.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.fragment.RefreshLayoutFragment
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.main.activity.ShowImageActivity
import com.example.myapplication.ui.main.adapter.ImageAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import com.example.myapplication.widget.OnRecyclerVIewItemClickLIstener
import com.scwang.smart.refresh.layout.api.RefreshLayout
import java.util.*
import kotlin.reflect.KClass

class MainFragment(content: String) : RefreshLayoutFragment<MainViewModel, MainFragmentBinding>() {
    lateinit var imageAdapter: ImageAdapter;
    var searchContent: String? = null

    companion object {
        fun newInstance(content: String):MainFragment{
           var instance = MainFragment(content)
            instance.searchContent=content
            return instance
        }
    }

    override fun getModelClass(): KClass<MainViewModel> {
        return MainViewModel::class
    }

    override fun getLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefreshUI(binding.refreshLayout)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.setViewmodel(viewModel)
        binding.recycler.setHasFixedSize(true)
        binding.recycler.setItemViewCacheSize(20)
        binding.recycler.addOnItemTouchListener(object : OnRecyclerVIewItemClickLIstener(binding.recycler) {
            override fun onItemClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
//                Log.e("wg", "onItemClickListener position = $position ur = ${viewModel.listData?.value?.get(position)?.middleURL}");
                var imgList = ArrayList<String>()
                imgList.add(viewModel.listData?.value?.get(position)!!.middleURL)
                ShowImageActivity.startShowImage(activity,imgList,0)
//                viewModel.getImageList()
            }

            override fun onItemLongClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
//                Log.e("wg", "onItemLongClickListener position = $position");
            }
        })

        var imageAdapter: ImageAdapter? = activity?.let { ImageAdapter(it) };
        binding.imageAdapter = imageAdapter;

//        viewModel.listData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
//            imageAdapter?.notifyDataSetChanged()
//        } )

        binding.executePendingBindings()
    }

    override fun initValue() {
        viewModel.searchContent.value = searchContent
        autoRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.liveDataGsm.value = ""
        viewModel.liveDataPn.value = 0
        viewModel.getImageList()
        finishBoth()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.getImageList()
        finishBoth()
    }
}
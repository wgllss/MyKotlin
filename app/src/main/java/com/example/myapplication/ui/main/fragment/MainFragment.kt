package com.example.myapplication.ui.main.fragment

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.common.framework.widget.OnRecyclerVIewItemClickLIstener
import com.example.myapplication.R
import com.example.myapplication.base.fragment.BaseMvvmFragment
import com.example.myapplication.databinding.MainFragmentBinding
import com.example.myapplication.ui.main.adapter.ImageAdapter
import com.example.myapplication.ui.main.viewmodel.MainViewModel
import java.util.*
import kotlin.reflect.KClass
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.observe
import com.example.myapplication.base.fragment.RefreshLayoutFragment
import com.scwang.smartrefresh.layout.api.RefreshLayout
import okhttp3.internal.notify

class MainFragment : RefreshLayoutFragment<MainViewModel, MainFragmentBinding>() {
    lateinit var imageAdapter: ImageAdapter;

    companion object {
        fun newInstance() = MainFragment()
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
        binding.recycler.addOnItemTouchListener(object :OnRecyclerVIewItemClickLIstener(binding.recycler) {
            override fun onItemClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
                Log.e("wg","onItemClickListener position = $position ur = ${viewModel.listData?.value?.get(position)?.middleURL}");

//                viewModel.getImageList()
            }

            override fun onItemLongClickListener(viewHolder: RecyclerView.ViewHolder?, position: Int) {
                Log.e("wg","onItemLongClickListener position = $position");
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
        autoRefresh()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.liveDataGsm.value=""
        viewModel.liveDataPn.value=0
        viewModel.getImageList()
        finishBoth()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.getImageList()
        finishBoth()
    }
}
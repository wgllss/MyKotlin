//package com.example.myapplication.base.binding_adapter;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.databinding.BindingAdapter;
//import androidx.paging.PagedListAdapter;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.example.myapplication.base.adapter.BaseDataBindingAdapter;
//import com.example.myapplication.uitls.ScreenUtils;
//import com.example.myapplication.widget.DividerGridItemDecoration;
//
//import java.util.List;
//
//public class RecycleBindingAdapter {
//
//    @BindingAdapter(value = {"itemColorStr", "itemWidth", "adapter", "listData"}, requireAll = false)
//    public static void setRecycleInfo(RecyclerView recyclerView, String itemColorStr, int itemWidth, PagedListAdapter adapter, List listData) {
//        try {
//            if (recyclerView != null) {
//
//                if (recyclerView.getAdapter() == null && adapter != null) {
//                    recyclerView.setAdapter(adapter);
//                }
//
//                recyclerView.setHasFixedSize(true);
//                if (itemWidth != 0 && recyclerView.getItemDecorationCount() == 0) {
//                    Context mContext = recyclerView.getContext();
//                    View itemDecoration = new View(mContext);
////        int size = (int) ScreenUtils.getIntToDip(2);
//                    int size = (int) ScreenUtils.getIntToDip(itemWidth);
//                    itemDecoration.setLayoutParams(new ViewGroup.LayoutParams(size, size));
////        itemDecoration.setBackgroundColor(Color.parseColor("#f0f2f5"));
//                    if (itemColorStr == null) {
//                        itemDecoration.setBackgroundColor(Color.TRANSPARENT);
//                    } else {
//                        itemDecoration.setBackgroundColor(Color.parseColor(itemColorStr));
//                    }
//                    recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext, GridLayoutManager.VERTICAL, itemDecoration));
//                }
////                if (adapter != null && listData != null) {
////                    adapter.notifyData(listData);
////                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(recyclerView.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//
////    @BindingAdapter(value = {"pageradapter", "currentitem","listData"},requireAll = false)
////    public static void setPagerAdapterInfo(ViewPager viewPager, PagerAdapter pagerAdapter, int currentItem,List listData) {
////        try {
////            if (viewPager != null) {
////                if (viewPager.getAdapter() != null && pagerAdapter != null) {
////                    viewPager.setAdapter(pagerAdapter);
////                }
////                viewPager.setCurrentItem(currentItem);
////
////            }
////
////        } catch (Exception e) {
////
////        }
////    }
//}

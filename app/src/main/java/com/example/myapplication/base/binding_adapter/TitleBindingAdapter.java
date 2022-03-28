//package com.example.myapplication.base.binding_adapter;
//
//import android.text.TextUtils;
//
//import androidx.databinding.BindingAdapter;
//
//
//public class TitleBindingAdapter {
//    @BindingAdapter(value = {"loginName", "loginPhone", "urlText"}, requireAll = false)
//    public static void setCommonBackTitle(ScaleCommonBackTitleView scaleCommonBackTitleView, String loginName, String loginPhone, String urlText) {
//        scaleCommonBackTitleView.setLoginName(loginName);
//        scaleCommonBackTitleView.setLoginPhone(loginPhone);
//        scaleCommonBackTitleView.setUrlText(urlText);
//    }
//
//    @BindingAdapter(value = {"loginName", "loginPhone", "urlText", "searchTitle"}, requireAll = false)
//    public static void setMainTitle(ScaleMainTitleView scaleMainTitleView, String loginName, String loginPhone, String urlText, String searchTitle) {
//        scaleMainTitleView.setLoginName(loginName);
//        scaleMainTitleView.setLoginPhone(loginPhone);
//        scaleMainTitleView.setUrlText(urlText);
//        if (!TextUtils.isEmpty(searchTitle)) {
//            scaleMainTitleView.setSearchTitle(searchTitle);
//        }
//    }
//
//}

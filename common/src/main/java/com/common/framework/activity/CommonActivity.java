package com.common.framework.activity;

import android.os.Bundle;

import com.common.framework.stack.ActivityManager;
import com.common.framework.utils.NoFastClickUtils;
import com.common.framework.utils.StatusBarUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author：atar
 * @date: 2019/5/8
 * @description:
 */
public class CommonActivity extends AppCompatActivity {

    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getActivityManager().pushActivity(this);
        StatusBarUtils.translucentStatusBar(this, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
        NoFastClickUtils.clearAll();
    }

    protected void setFragment(int replaceLayoutID, Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        f.setUserVisibleHint(true);
        ft.replace(replaceLayoutID, f);
        // ft.commit();
        ft.commitAllowingStateLoss();
    }

    /**
     * 是否显示fragment
     *
     * @param mFragment
     * @author :Atar
     * @createTime:2015-6-23上午9:56:22
     * @version:1.0.0
     * @modifyTime:
     * @modifyAuthor:
     * @description:
     */
    public void isShowFragment(Fragment mFragment, boolean isShow) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (mFragment != null) {
            if (isShow) {
                ft.show(mFragment);
            } else {
                ft.hide(mFragment);
            }
            ft.commitAllowingStateLoss();
        }
    }
}

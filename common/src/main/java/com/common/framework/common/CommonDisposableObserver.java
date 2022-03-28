package com.common.framework.common;

import com.tencent.bugly.crashreport.CrashReport;

import io.reactivex.observers.DisposableObserver;

/**
 * 公共处理Rxjava回调
 *
 * @author：atar
 * @date: 2019/2/20
 * @description:
 */

public class CommonDisposableObserver<T> extends DisposableObserver<T> {
    private int what;
    private int which1;
    private int which2;
    private int which3;
    private CommonRereshUInteface mCommonRereshUInteface;

    public CommonDisposableObserver(CommonRereshUInteface mCommonRereshUInteface) {
        this.mCommonRereshUInteface = mCommonRereshUInteface;
    }

    public CommonDisposableObserver(int what, CommonRereshUInteface mCommonRereshUInteface) {
        this.what = what;
        this.mCommonRereshUInteface = mCommonRereshUInteface;
    }

    public CommonDisposableObserver(int what, int which1, CommonRereshUInteface mCommonRereshUInteface) {
        this.what = what;
        this.which1 = which1;
        this.mCommonRereshUInteface = mCommonRereshUInteface;
    }

    public CommonDisposableObserver(int what, int which1, int which2, CommonRereshUInteface mCommonRereshUInteface) {
        this.what = what;
        this.which1 = which1;
        this.which2 = which2;
        this.mCommonRereshUInteface = mCommonRereshUInteface;
    }

    public CommonDisposableObserver(int what, int which1, int which2, int which3, CommonRereshUInteface mCommonRereshUInteface) {
        this.what = what;
        this.which1 = which1;
        this.which2 = which2;
        this.which3 = which3;
        this.mCommonRereshUInteface = mCommonRereshUInteface;
    }

    @Override
    public void onNext(T t) {
        try {
            if (!isDisposed() && mCommonRereshUInteface != null) {
                mCommonRereshUInteface.onNext(what, which1, which2, which3, t);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CrashReport.postCatchedException(e);
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            if (!isDisposed() && mCommonRereshUInteface != null && e != null) {
                mCommonRereshUInteface.onError(what, which1, which2, which3, 0, e.getMessage(), "");
            }
        } catch (Exception ex) {
            e.printStackTrace();
            CrashReport.postCatchedException(ex);
        }
    }

    @Override
    public void onComplete() {
        try {
            if (!isDisposed() && mCommonRereshUInteface != null) {
                mCommonRereshUInteface.onComplete(what, which1, which2, which3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            CrashReport.postCatchedException(e);
        }
    }
}

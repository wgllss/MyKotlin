package com.common.framework.rxextends;

import io.reactivex.observers.DisposableObserver;

/**
 * @author：atar
 * @date: 2019/1/30
 * @description:
 */
public class RxDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T o) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}

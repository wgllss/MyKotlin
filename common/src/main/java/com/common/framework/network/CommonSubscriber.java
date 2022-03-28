package com.common.framework.network;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author：atar
 * @date: 2019/5/8
 * @description:
 */
public class CommonSubscriber extends ResourceSubscriber<String> {


    @Override
    public void onNext(String s) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}

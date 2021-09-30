package com.example.wechatmoments.utils;

import android.content.Context;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseResObserver<T> implements Observer<T> {

    private Lifecycle mLifeCycle;

    public BaseResObserver() {
    }

    public BaseResObserver(Lifecycle lifecycle) {
        mLifeCycle = lifecycle;
    }

    public BaseResObserver(Context context) {
        if (context instanceof LifecycleOwner) {
            mLifeCycle = ((LifecycleOwner) context).getLifecycle();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    private boolean shouldPostResult() {
        if (mLifeCycle != null) {
            return !mLifeCycle.getCurrentState().isAtLeast(Lifecycle.State.CREATED);
        }
        return false;
    }

    @Override
    public void onNext(T baseInfo) {
        if (baseInfo == null) {
            return;
        }

        if(shouldPostResult()) {
            return;
        }
        onRequestEnd();

        try {
            onSuccess(baseInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        if(shouldPostResult()) {
            return;
        }
        onRequestEnd();

        onFailure(e);
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     * @param t
     */
    protected abstract void onSuccess(T t);

    /**
     * 返回失败
     * @param e
     */
    protected void onFailure(Throwable e) {

    }

    protected void onRequestStart() {

    }

    protected void onRequestEnd() {

    }

}

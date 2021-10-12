package com.example.wechatmoments.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;

import com.example.wechatmoments.constant.Constant;
import com.example.wechatmoments.data.model.DelayTime;
import com.example.wechatmoments.data.repository.VoidRepository;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class SplashViewModel extends BaseViewModel<VoidRepository> implements LifecycleObserver {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private MutableLiveData<DelayTime> delayToTime = new MutableLiveData<>();

    /**
     * 构造方法
     *
     * @param application Application
     */
    public SplashViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<DelayTime> getDelayToTime() {
        if (delayToTime == null) {
            delayToTime = new MutableLiveData<>();
        }
        return delayToTime;
    }

    public void delayTime() {
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    delayToTime.postValue(new DelayTime(Constant.NOT_NEED_FINISH_SPLASH,aLong + 1));
                    if (aLong >= Constant.FINISH_SPLASH) {
                        dispose();
                        delayToTime.postValue(new DelayTime(Constant.NEED_FINISH_SPLASH,aLong + 1));
                    }

                });
        mCompositeDisposable.add(subscribe);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        dispose();
    }

    private void dispose() {
        mCompositeDisposable.dispose();
    }
}

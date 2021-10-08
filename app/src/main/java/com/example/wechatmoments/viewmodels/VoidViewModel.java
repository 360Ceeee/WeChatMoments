package com.example.wechatmoments.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;

import com.example.wechatmoments.data.repository.VoidRepository;

public class VoidViewModel extends BaseViewModel<VoidRepository> implements LifecycleObserver {

    public VoidViewModel(@NonNull Application application) {
        super(application);
    }
}

package com.example.wechatmoments.utils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechatmoments.viewmodels.BaseViewModel;

import io.reactivex.annotations.NonNull;

public abstract class AbstractLifeCycleActivity<T extends BaseViewModel> extends BaseActivity {

    protected T viewModel;

    @Override
    public void initViews(Bundle savedInstanceState) {
        viewModel = viewModelProviders(this, ObjectClassUtil.getInstance(this, 0));
        dataObserver();
        initEvent();
    }

    protected <VM extends ViewModel> VM viewModelProviders(AppCompatActivity fragment, @NonNull Class<VM> modelClass) {
        return new ViewModelProvider(fragment).get(modelClass);
    }

    protected void dataObserver() {

    }

    protected void initEvent() {

    }


}

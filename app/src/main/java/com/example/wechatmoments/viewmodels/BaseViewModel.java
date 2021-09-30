package com.example.wechatmoments.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.wechatmoments.utils.AbstractRepository;
import com.example.wechatmoments.utils.ObjectClassUtil;

public class BaseViewModel<T extends AbstractRepository> extends AndroidViewModel {

    public T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mRepository = ObjectClassUtil.getNewInstance(this, 0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository.removeDisposable();
        }
    }
}

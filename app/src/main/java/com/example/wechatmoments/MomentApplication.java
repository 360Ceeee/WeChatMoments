package com.example.wechatmoments;

import android.app.Application;

import com.example.wechatmoments.utils.Glide.GlideImageLoader;
import com.example.wechatmoments.utils.http.HttpHelper;
import com.example.wechatmoments.utils.http.URL;
import com.example.wechatmoments.view.widget.ToastView;
import com.example.wechatmoments.view.widget.nineimg.NineGridView;

public class MomentApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttp();
        initNineImage();
        initToast();
    }

    private void initOkHttp() {
        new HttpHelper.Builder(this)
                .initOkHttp()
                .createRetrofit(URL.BASE_URL)
                .build();
    }

    private void initNineImage() {
        NineGridView.setImageLoader(new GlideImageLoader());
    }

    private void initToast() {
        ToastView.init(this);
    }


}

package com.example.wechatmoments.view.activity;

import android.os.Bundle;

import com.example.wechatmoments.constant.Constant;
import com.example.wechatmoments.utils.AbstractLifeCycleActivity;
import com.example.wechatmoments.utils.statusbar.StatusBarUtil;
import com.example.wechatmoments.viewmodels.SplashViewModel;
import com.example.wechatmoments.R;

public class SplashActivity extends AbstractLifeCycleActivity<SplashViewModel> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this, false);
        StatusBarUtil.hideNavigationBar(this);
        mViewModel.delayTime();
    }

    @Override
    protected void dataObserver() {
        super.dataObserver();
        mViewModel.getDelayToTime().observe(this, delayTimeBean -> {
            if (delayTimeBean != null) {
                if (Constant.NEED_FINISH_SPLASH == delayTimeBean.getState()) {
                    MomentActivity.navigateToMomentActivity(SplashActivity.this);
                    finish();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}

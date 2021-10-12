package com.example.wechatmoments.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.wechatmoments.utils.AbstractLifeCycleActivity;
import com.example.wechatmoments.utils.Glide.GlideUtil;
import com.example.wechatmoments.utils.statusbar.StatusBarUtil;
import com.example.wechatmoments.viewmodels.VoidViewModel;
import com.gyf.immersionbar.ImmersionBar;
import com.example.wechatmoments.R;

import butterknife.BindView;

public class CustomBitmapActivity extends AbstractLifeCycleActivity<VoidViewModel> {

    private static final String URL = "url";
    private static final String AVATAR = "avatar";
    @BindView(R.id.iv_finish_bit)
    View mViewFinish;
    @BindView(R.id.iv_bitmap)
    ImageView mImageView;

    public static void navigateToCustomBitmapActivity(Context context, String url, boolean isAvatar) {
        Intent intent = new Intent(context, CustomBitmapActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(AVATAR, isAvatar);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this, false);
        GlideUtil.loadWithSelfBitmap(this, getIntent().getStringExtra(URL), mImageView,
                getIntent().getBooleanExtra(URL, false) ? R.drawable.userimage : R.drawable.background);
        mViewFinish.setOnClickListener(view -> finish());
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).navigationBarColor(R.color.black).init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bitmap;
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}

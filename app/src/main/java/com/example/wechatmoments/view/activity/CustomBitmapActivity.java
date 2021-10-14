package com.example.wechatmoments.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wechatmoments.utils.AbstractLifeCycleActivity;
import com.example.wechatmoments.utils.statusbar.StatusBarUtil;
import com.example.wechatmoments.viewmodels.VoidViewModel;
import com.gyf.immersionbar.ImmersionBar;
import com.example.wechatmoments.R;

import butterknife.BindView;

public class CustomBitmapActivity extends AbstractLifeCycleActivity<VoidViewModel> {

    private static final String URL = "url";
    private static final String AVATAR = "avatar";
    @BindView(R.id.iv_finish_bit)
    View viewFinish;
    @BindView(R.id.iv_bitmap)
    ImageView imageView;

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
        Glide.with(this)
                .load(getIntent().getStringExtra(URL))
                .placeholder(getIntent().getBooleanExtra(URL, false) ? R.mipmap.icon_default_small_head : R.mipmap.default_place_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        viewFinish.setOnClickListener(view -> finish());
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

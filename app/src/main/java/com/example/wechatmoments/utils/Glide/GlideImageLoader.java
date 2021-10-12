package com.example.wechatmoments.utils.Glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.example.wechatmoments.R;
import com.example.wechatmoments.view.widget.nineimg.NineGridView;

public class GlideImageLoader implements NineGridView.ImageLoader{

    @Override
    public void onDisplayImage(Context context, ImageView imageView, String url){
        GlideUtil.load(context, url, imageView, R.drawable.background);
    }

    @Override
    public Bitmap getCacheImage(String url){
        return null;
    }
}

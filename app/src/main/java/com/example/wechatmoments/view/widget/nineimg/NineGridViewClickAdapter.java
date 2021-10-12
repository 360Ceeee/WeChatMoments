package com.example.wechatmoments.view.widget.nineimg;

import android.content.Context;

import com.example.wechatmoments.data.model.ImageInfo;

import java.util.List;

public class NineGridViewClickAdapter extends NineGridViewAdapter {

    public NineGridViewClickAdapter(Context context, List<ImageInfo> imageInfo) {
        super(context, imageInfo);
    }

    @Override
    protected void onImageItemClick(Context context, NineGridView nineGridView, int index, List<ImageInfo> imageInfo) {

    }


}

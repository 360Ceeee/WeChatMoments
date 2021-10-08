package com.example.wechatmoments.utils.bitmapcache;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class MomentBitmapManager {
    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    private MomentBitmapManager(Context context) {
        mLocalCacheUtils = new LocalCacheUtils(context);
        mMemoryCacheUtils = new MemoryCacheUtils();
        mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
    }

    private static MomentBitmapManager instance;

    public static MomentBitmapManager getInstance(Context context) {
        if (instance == null) {
            synchronized (MomentBitmapManager.class) {
                if (instance == null) {
                    instance = new MomentBitmapManager(context);
                }
            }
        }
        return instance;
    }


    public void display(ImageView ivPic, String url, int placeId) {
        ivPic.setImageResource(placeId);
        Bitmap bitmap;

        //读取内存缓存
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            return;
        }

        //读取磁盘缓存
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            ivPic.setImageBitmap(bitmap);
            return;
        }

        //读取网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic, url);
    }

}

package com.example.wechatmoments.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Images {
    /**
     * "url": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS3AqhlL_Ubqa8G_usBmy3q8z0cg8JieuVb1pV2nie4vikVEP5U"
     */

    private String url;

    protected Images(Parcel in) {
        url = in.readString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

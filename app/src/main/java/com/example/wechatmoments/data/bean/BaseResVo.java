package com.example.wechatmoments.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseResVo implements Parcelable {


    public static final Creator<BaseResVo> CREATOR = new Creator<BaseResVo>() {

        @Override
        public BaseResVo createFromParcel(Parcel in) {
            return new BaseResVo();
        }

        @Override
        public BaseResVo[] newArray(int size) {
            return new BaseResVo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

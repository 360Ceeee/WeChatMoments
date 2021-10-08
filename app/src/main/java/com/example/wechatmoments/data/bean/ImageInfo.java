package com.example.wechatmoments.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageInfo implements Parcelable {
    public String mThumbnailUrl;
    public String mBigImageUrl;
    public int mImageViewHeight;
    public int mImageViewWidth;
    public int mImageViewX;
    public int mImageViewY;

    public ImageInfo(){

    }

    protected ImageInfo(Parcel in) {
        mThumbnailUrl = in.readString();
        mBigImageUrl = in.readString();
        mImageViewHeight = in.readInt();
        mImageViewWidth = in.readInt();
        mImageViewX = in.readInt();
        mImageViewY = in.readInt();
    }

    public static final Creator<ImageInfo> CREATOR = new Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel in) {
            return new ImageInfo(in);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String sThumbnailUrl) {
        this.mThumbnailUrl = sThumbnailUrl;
    }

    public String getBigImageUrl() {
        return mBigImageUrl;
    }

    public void setBigImageUrl(String mBigImageUrl) {
        this.mBigImageUrl = mBigImageUrl;
    }

    public int getImageViewHeight() {
        return mImageViewHeight;
    }

    public void setImageViewHeight(int mImageViewHeight) {
        this.mImageViewHeight = mImageViewHeight;
    }

    public int getImageViewWidth() {
        return mImageViewWidth;
    }

    public void setImageViewWidth(int mImageViewWidth) {
        this.mImageViewWidth = mImageViewWidth;
    }

    public int getImageViewX() {
        return mImageViewX;
    }

    public void setImageViewX(int mImageViewX) {
        this.mImageViewX = mImageViewX;
    }

    public int getImageViewY() {
        return mImageViewY;
    }

    public void setImageViewY(int mImageViewY) {
        this.mImageViewY = mImageViewY;
    }

    @NonNull
    @Override
    public String toString() {
        return "ImageInfo{" +
                "imageViewY=" + mImageViewY +
                ", imageViewX=" + mImageViewX +
                ", imageViewWidth=" + mImageViewWidth +
                ", imageViewHeight=" + mImageViewHeight +
                ", bigImageUrl='" + mBigImageUrl + '\'' +
                ", thumbnailUrl='" + mThumbnailUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mThumbnailUrl);
        dest.writeString(mBigImageUrl);
        dest.writeInt(mImageViewHeight);
        dest.writeInt(mImageViewWidth);
        dest.writeInt(mImageViewX);
        dest.writeInt(mImageViewY);
    }
}

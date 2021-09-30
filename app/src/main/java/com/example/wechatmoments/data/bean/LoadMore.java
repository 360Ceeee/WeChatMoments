package com.example.wechatmoments.data.bean;

public class LoadMore {
    private boolean mIsLoadMoreSuccess;
    private boolean mHasMoreData;

    public boolean isLoadMoreSuccess() {
        return mIsLoadMoreSuccess;
    }

    public void setLoadMoreSuccess(boolean isLoadMoreSuccess) {
        mIsLoadMoreSuccess = isLoadMoreSuccess;
    }

    public boolean isHasMoreData() {
        return mHasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        mHasMoreData = hasMoreData;
    }
}

package com.example.wechatmoments.data.model;

public class LoadMore {
    private boolean isLoadMoreSuccess;
    private boolean hasMoreData;

    public boolean isLoadMoreSuccess() {
        return isLoadMoreSuccess;
    }

    public void setLoadMoreSuccess(boolean isLoadMoreSuccess) {
        this.isLoadMoreSuccess = isLoadMoreSuccess;
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }
}

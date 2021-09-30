package com.example.wechatmoments.data.bean;

public class LoadMore {
    private boolean isLoadMoreSuccess;
    private boolean hasMoreData;

    public boolean isLoadMoreSuccess() {
        return isLoadMoreSuccess;
    }

    public void setLoadMoreSuccess(boolean loadMoreSuccess) {
        isLoadMoreSuccess = loadMoreSuccess;
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }
}

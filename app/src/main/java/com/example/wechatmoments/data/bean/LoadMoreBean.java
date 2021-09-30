package com.example.wechatmoments.data.bean;

public class LoadMoreBean {
    private boolean isLoadMoreSuccess;
    private boolean hasMoreData;

    public boolean isLoadMoreSuccess() {
        return isLoadMoreSuccess;
    }

    public void setLoadMoreSuccess(Boolean loadMoreSuccess) {
        isLoadMoreSuccess = loadMoreSuccess;
    }

    public boolean isHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(Boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }
}

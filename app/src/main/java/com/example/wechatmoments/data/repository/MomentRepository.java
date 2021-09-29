package com.example.wechatmoments.data.repository;

import com.example.wechatmoments.data.bean.MomentListBean;
import com.example.wechatmoments.data.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;

public class MomentRepository extends VoidRepository{

    public MomentRepository() {

    }

    public Observable<List<MomentListBean>> getMomentList() {
        return apiService.getMomentList();
    }

    public Observable<UserInfoBean> getUserInfo() {
        return apiService.getUserInfo();
    }
}

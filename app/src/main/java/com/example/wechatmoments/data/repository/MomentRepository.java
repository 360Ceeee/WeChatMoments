package com.example.wechatmoments.data.repository;

import com.example.wechatmoments.data.bean.MomentList;
import com.example.wechatmoments.data.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public class MomentRepository extends VoidRepository{

    public MomentRepository() {

    }

    public Observable<List<MomentList>> getMomentList() {
        return apiService.getMomentList();
    }

    public Observable<UserInfo> getUserInfo() {
        return apiService.getUserInfo();
    }
}

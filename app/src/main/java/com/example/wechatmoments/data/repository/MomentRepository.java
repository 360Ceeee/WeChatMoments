package com.example.wechatmoments.data.repository;

import com.example.wechatmoments.data.model.MomentList;
import com.example.wechatmoments.data.model.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public class MomentRepository extends VoidRepository{

    public Observable<List<MomentList>> getMomentList() {
        return apiService.getMomentList();
    }

    public Observable<UserInfo> getUserInfo() {
        return apiService.getUserInfo();
    }
}

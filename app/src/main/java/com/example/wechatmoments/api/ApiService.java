package com.example.wechatmoments.api;

import com.example.wechatmoments.data.model.MomentList;
import com.example.wechatmoments.data.model.UserInfo;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    /**
     * 请求用户信息
     *
     * @return
     */
    @GET("user/jsmith")
    Observable<UserInfo> getUserInfo();

    /**
     * 请求列表
     *
     * @return
     */
    @GET("user/jsmith/tweets")
    Observable<List<MomentList>> getMomentList();


}

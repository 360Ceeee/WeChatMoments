package com.example.wechatmoments.utils;

import com.example.wechatmoments.api.ApiService;
import com.example.wechatmoments.utils.AbstractRepository;
import com.example.wechatmoments.utils.http.HttpHelper;

public class BaseRepository extends AbstractRepository {

    protected ApiService apiService;

    public BaseRepository() {
        apiService = HttpHelper.getInstance().create(ApiService.class);
    }
}

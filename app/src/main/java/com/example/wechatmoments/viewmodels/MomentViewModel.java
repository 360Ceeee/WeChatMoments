package com.example.wechatmoments.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;

import com.example.wechatmoments.constant.Constant;
import com.example.wechatmoments.data.bean.LoadMore;
import com.example.wechatmoments.data.bean.MomentList;
import com.example.wechatmoments.data.bean.UserInfo;
import com.example.wechatmoments.data.repository.MomentRepository;
import com.example.wechatmoments.utils.BaseResObserver;
import com.example.wechatmoments.data.bean.MemoryMomentStore;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MomentViewModel extends BaseViewModel<MomentRepository> implements LifecycleObserver {

    private MutableLiveData<UserInfo> userInfoData;

    private MutableLiveData<List<MomentList>> momentList;

    private MutableLiveData<LoadMore> loadMore;

    private int page = Constant.ONE;


    public MomentViewModel(@NonNull Application application) {
        super(application);
        userInfoData = new MutableLiveData<>();
        momentList = new MutableLiveData<>();
    }

    public MutableLiveData<UserInfo> getUserInfoData() {
        if (userInfoData == null) {
            userInfoData = new MutableLiveData<>();
        }
        return userInfoData;
    }

    public MutableLiveData<List<MomentList>> getMomentList() {
        if (momentList == null) {
            momentList = new MutableLiveData<>();
        }
        return momentList;
    }

    public MutableLiveData<LoadMore> getLoadMore() {
        if (loadMore == null) {
            loadMore = new MutableLiveData<>();
        }
        return loadMore;
    }

    public boolean isRefresh() {
        return page == Constant.ONE;
    }

    /**
     * 获取用户信息
     * @param context Context
     */
    public void getUserInfo(Context context) {
        mRepository.getUserInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResObserver<UserInfo>(context) {
            @Override
            protected void onSuccess(UserInfo s) {
                userInfoData.setValue(s);
            }

            @Override
            protected void onFailure(Throwable e) {
                super.onFailure(e);
                userInfoData.setValue(null);
            }
        });
    }

    /**
     * 获取列表
     * @param context Context
     */
    public void getMomentList(Context context){
        mRepository.getMomentList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BaseResObserver<List<MomentList>>(context) {
            @Override
            protected void onSuccess(List<MomentList> momentLists) {
                if (momentLists != null && momentLists.size() != 0) {
                    int size = momentLists.size();
                    int listLength = size / Constant.MAX_SIZE;
                    int listOther = size % Constant.MAX_SIZE;
                    MemoryMomentStore.totalPage = listOther == 0 ? listLength : (listLength + 1);

                    momentList.setValue(getLocalMaxSize(momentLists));
                    MemoryMomentStore.getInstance().saveMomentList(momentLists);
                }
            }

            @Override
            protected void onFailure(Throwable e){
                super.onFailure(e);
                momentList.setValue(null);
            }
        });
    }

    public void refreshData(Context context) {
        page = Constant.ONE;
        getMomentList(context);
        getUserInfo(context);
    }

    public void loadMoreData() {
        if (page < MemoryMomentStore.totalPage) {
            page++;
            momentList.setValue(MemoryMomentStore.getInstance().getPartMomentList(page));
            setLoadMoreState(true);
        } else {
            setLoadMoreState(false);
        }
    }

    private void setLoadMoreState(Boolean hasMore) {
        LoadMore loadMore = new LoadMore();
        loadMore.setLoadMoreSuccess(true);
        loadMore.setHasMoreData(hasMore);
        this.loadMore.setValue(loadMore);
    }

    private List<MomentList> getLocalMaxSize(List<MomentList> list) {
        int maxSize = Constant.MAX_SIZE;

        if (list.size() <= maxSize) {
            return list;
        }

        return list.subList(0, maxSize);
    }




}

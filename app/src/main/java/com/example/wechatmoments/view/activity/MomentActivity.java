package com.example.wechatmoments.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wechatmoments.R;
import com.example.wechatmoments.constant.Constant;
import com.example.wechatmoments.constant.LoadingState;
import com.example.wechatmoments.data.model.MomentList;
import com.example.wechatmoments.data.model.UserInfo;
import com.example.wechatmoments.utils.AbstractLifeCycleActivity;
import com.example.wechatmoments.utils.Glide.GlideUtil;
import com.example.wechatmoments.utils.statusbar.StatusBarUtil;
import com.example.wechatmoments.view.MomentAdapter;
import com.example.wechatmoments.view.widget.StatusView;
import com.example.wechatmoments.view.widget.ToastView;
import com.example.wechatmoments.viewmodels.MomentViewModel;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MomentActivity extends AbstractLifeCycleActivity<MomentViewModel> {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;

    @BindView(R.id.sv_status)
    StatusView mStatusView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.rl_bar_title)
    View mRlTitleView;

    @BindView(R.id.iv_user_bg)
    ImageView mIvSelfBg;

    @BindView(R.id.iv_self_head)
    ImageView mIvSelfHead;

    @BindView(R.id.tv_self_name)
    TextView mTvSelfName;

    private List<MomentList> mList;

    private MomentAdapter mAdapter;

    private int mAppBarLayoutHeight;

    private long mExitTime;

    private int mTitleViewHeight;


    public static void navigateToMomentActivity(Context context) {
        Intent intent = new Intent(context, MomentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersiveStatusBar(this, false);
        initView();
        mSwipeRefreshLayout.setRefreshing(true);
        mViewModel.refreshData(this);
    }

    private void initView() {
        mSwipeRefreshLayout.setProgressViewEndTarget(false, dip2px());
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MomentAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mAppBarLayout.post(() -> {
            mTitleViewHeight = mRlTitleView.getHeight();
            mAppBarLayoutHeight = mAppBarLayout.getHeight();
        });
        mRlTitleView.setOnClickListener(v -> mRecyclerView.scrollToPosition(Constant.ZERO));

    }

    private int dip2px() {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (100 * scale);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        appBarEvent();
        refreshEvent();
        recyclerViewEvent();
    }

    /**
     * 加载更多
     */
    private void recyclerViewEvent() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                try {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //改变加载的状态
                        mAdapter.setFootView(LoadingState.LOADING);
                        mViewModel.loadMoreData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 刷新
     */
    private void refreshEvent() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> mViewModel.refreshData(MomentActivity.this));
    }

    /**
     * appBarEvent事件
     */
    private void appBarEvent() {
        mAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset >= Constant.ZERO) {
                mSwipeRefreshLayout.setEnabled(true);
                //将标题栏的颜色设置为完全不透明状态
                mRlTitleView.setAlpha(0f);
                mStatusView.setAlpha(0f);
                StatusBarUtil.setImmersiveStatusBar(MomentActivity.this, false);
            } else {
                if (!mSwipeRefreshLayout.isRefreshing()) {
                    mSwipeRefreshLayout.setEnabled(false);
                }
                int abs = Math.abs(verticalOffset);
                if (abs <= mAppBarLayoutHeight - (mTitleViewHeight + StatusBarUtil.getStatusBarHeight(MomentActivity.this))) {
                    float alpha = (float) abs / mAppBarLayoutHeight;
                    mRlTitleView.setAlpha(alpha);
                    mStatusView.setAlpha(alpha);
                    StatusBarUtil.setImmersiveStatusBar(MomentActivity.this, false);
                } else {
                    //将标题栏的颜色设置为完全不透明状态
                    mRlTitleView.setAlpha(1.0f);
                    mStatusView.setAlpha(1.0f);
                    StatusBarUtil.setImmersiveStatusBar(MomentActivity.this, true, ContextCompat.getColor(MomentActivity.this, R.color.home_status_bar_color));
                }
            }
        });
    }

    /**
     * 数据回调
     */
    @Override
    protected void dataObserver() {
        super.dataObserver();
        observerUserInfo();
        observerMomentList();
        observerLoadMore();
    }

    /**
     * 分页回调
     */
    private void observerLoadMore() {
        mViewModel.getLoadMore().observe(this, loadMoreBean -> {
            //加载更多成功 判断是否还有更多数据
            if (loadMoreBean.isHasMoreData()) {
                mAdapter.setFootView(LoadingState.LOADING_COMPLETE);
            } else {
                mAdapter.setFootView(LoadingState.LOADING_NO_MORE);
            }
        });
    }

    /**
     * ，列表数据回调
     */
    private void observerMomentList() {
        mViewModel.getMomentList().observe(this, momentListBeans -> {
            mSwipeRefreshLayout.setRefreshing(false);
            if (momentListBeans != null && momentListBeans.size() != 0) {
                if (mViewModel.isRefresh()) {
                    mList.clear();
                }
                mList.addAll(momentListBeans);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 用户信息回调
     */
    private void observerUserInfo() {
        mViewModel.getUserInfoData().observe(this, userInfo -> {
            mSwipeRefreshLayout.setRefreshing(false);
            if (userInfo != null) {
                setUserInfo(userInfo);
            }
        });
    }

    /**
     * 设置用户信息
     *
     * @param userInfo 用户信息
     */
    private void setUserInfo(UserInfo userInfo) {
        mTvSelfName.setText(userInfo.getUsername());
        GlideUtil.load(this, userInfo.getProfileImage(), mIvSelfBg, R.drawable.background);
        GlideUtil.load(this, userInfo.getAvatar(), mIvSelfHead, R.drawable.userimage);
        mIvSelfHead.setOnClickListener(view -> CustomBitmapActivity.navigateToCustomBitmapActivity(MomentActivity.this, userInfo.getAvatar(), true));
        mIvSelfBg.setOnClickListener(view -> CustomBitmapActivity.navigateToCustomBitmapActivity(MomentActivity.this, userInfo.getProfileImage(), false));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > Constant.MAX_TIME_MILLIS) {
            ToastView.showToast("再次点击返回键退出界面");
            mExitTime = System.currentTimeMillis();
        } else {
            moveTaskToBack(true);
        }
    }
}

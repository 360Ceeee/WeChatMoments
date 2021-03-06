package com.example.wechatmoments.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechatmoments.constant.LoadingState;
import com.example.wechatmoments.data.model.Comments;
import com.example.wechatmoments.data.model.ImageInfo;
import com.example.wechatmoments.data.model.Images;
import com.example.wechatmoments.data.model.MomentList;
import com.example.wechatmoments.R;
import com.example.wechatmoments.data.model.Sender;
import com.example.wechatmoments.utils.Glide.GlideUtil;
import com.example.wechatmoments.view.activity.CustomBitmapActivity;
import com.example.wechatmoments.view.widget.ToastView;
import com.example.wechatmoments.view.widget.comment.CommentsView;
import com.example.wechatmoments.view.widget.nineimg.NineGridView;
import com.example.wechatmoments.view.widget.nineimg.NineGridViewClickAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MomentAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<MomentList> list;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOT = 2;

    public MomentAdapter(Context context, List<MomentList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //条目
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (viewType == TYPE_ITEM) {
            return new MomentViewHolder(inflater.inflate(R.layout.item_moment, parent, false));
        } else {
            //底部
            return new FootViewHolder(inflater.inflate(R.layout.item_recyclerview_footer, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_ITEM) {
            MomentViewHolder holder = (MomentViewHolder) viewHolder;
            adaptData(holder, position);
        } else {
            FootViewHolder holder = (FootViewHolder) viewHolder;
            holder.itemView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 渲染数据
     *
     * @param holder   MomentViewHolder
     * @param position int位置
     */
    private void adaptData(@NonNull MomentViewHolder holder, int position) {
        holder.viewLine.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        MomentList momentList = list.get(position);
        if (momentList == null) {
            return;
        }
        //设置用户信息 名称 时间 内容
        setSomeInfo(holder, momentList);
        //设置九宫格图片数据
        setNineImg(holder, momentList);
        //设置评论数据
        setComment(holder, momentList);

    }

    /**
     * 设置用户信息 名称 时间 内容
     *
     * @param holder         MomentViewHolder
     * @param momentList MomentList
     */
    private void setSomeInfo(@NonNull MomentViewHolder holder, MomentList momentList) {
        //设置时间
        holder.tvTime.setText(getCurrentTime());

        Sender sender = momentList.getSender();
        String avatar = sender.getAvatar();
        //sender在数据结构中已经判空
        GlideUtil.loadRoundedCorner(context, avatar, holder.ivHead, R.mipmap.icon_default_small_head);
        holder.ivHead.setOnClickListener(view -> {
            if (!TextUtils.isEmpty(avatar)) {
                CustomBitmapActivity.navigateToCustomBitmapActivity(context, avatar, true);
            } else {
                ToastView.showToast("数据异常");
            }
        });

        holder.tvName.setText(sender.getUsername());

        holder.tvDesc.setText(momentList.getContent());
    }

    /**
     * 设置评论数据
     *
     * @param holder         MomentViewHolder
     * @param momentList MomentList
     */
    private void setComment(@NonNull MomentViewHolder holder, MomentList momentList) {
        //适配评论
        List<Comments> comments = momentList.getComments();
        if (comments != null && comments.size() != 0) {
            holder.commentRoot.setVisibility(View.VISIBLE);
            holder.commentsView.setList(comments);
            holder.commentsView.notifyDataSetChanged();
        } else {
            holder.commentRoot.setVisibility(View.GONE);
        }
    }

    /**
     * 设置九宫格图片数据
     *
     * @param holder         MomentViewHolder
     * @param momentListBean MomentListBean
     */
    private void setNineImg(@NonNull MomentViewHolder holder, MomentList momentListBean) {
        //设置九宫格图片
        List<Images> images = momentListBean.getImages();
        List<ImageInfo> imageInfo = new ArrayList<>();
        if (images != null && images.size() != 0) {
            holder.nineGridView.setVisibility(View.VISIBLE);
            for (Images image : images) {
                imageInfo.add(getImageInfo(image));
            }
            NineGridViewClickAdapter adapter = new NineGridViewClickAdapter(context, imageInfo);
            holder.nineGridView.setAdapter(adapter);

        } else {
            holder.nineGridView.setVisibility(View.GONE);
        }
    }

    private ImageInfo getImageInfo(Images bean) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setBigImageUrl(bean.getUrl());
        imageInfo.setThumbnailUrl(bean.getUrl());
        return imageInfo;
    }


    @Override
    public int getItemCount() {
        if (list != null && list.size() != 0) {
            return list.size() + 1;
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemCount() == position + 1) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }

    static class MomentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.ngv)
        NineGridView nineGridView;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.rl_comment)
        View commentRoot;
        @BindView(R.id.cv)
        CommentsView commentsView;
        @BindView(R.id.tv_time)
        TextView tvTime;


        public MomentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setFootView(int loadingState) {
        if (loadingState == LoadingState.LOADING) {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("正在加载...");
        } else if (loadingState == LoadingState.LOADING_ERROR) {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("加载出错~");
        } else if (loadingState == LoadingState.LOADING_COMPLETE) {
            progressBar.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        } else if (loadingState == LoadingState.LOADING_NO_MORE) {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setText("更多精彩内容,敬请期待~");
        }
    }


    /**
     * 底部内容
     */
    TextView textView;
    /**
     * 底部进度条
     */
    ProgressBar progressBar;

    private class FootViewHolder extends RecyclerView.ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.we_media_loading);
            progressBar = (ProgressBar) itemView.findViewById(R.id.we_media_progress);
        }
    }

    private static String getCurrentTime() {
        try {
            return formatDate(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return "currentTime";
        }
    }

    private static String formatDate(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}


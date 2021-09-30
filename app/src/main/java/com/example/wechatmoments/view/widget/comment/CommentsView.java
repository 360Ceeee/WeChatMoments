package com.example.wechatmoments.view.widget.comment;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.wechatmoments.data.bean.Comments;
import com.example.wechatmoments.data.bean.Sender;
import com.example.wechatmoments.view.widget.ToastView;

import java.util.List;

public class CommentsView extends LinearLayout {

    private final Context mContext;
    private List<Comments> mData;
    private onItemClickListener mListener;

    public CommentsView(Context context) {
        this(context, null);
    }
    public CommentsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommentsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        mContext = context;
    }

    public void setList(List<Comments> list) {
        mData = list;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    public void notifyDataSetChanged() {
        removeAllViews();
        if (mData == null || mData.size() <= 0) {
            return;
        }
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        for (int i = 0; i < mData.size(); i++) {
            View view = getView(i);
            if (view != null) {
                addView(view, i, layoutParams);
            }
        }
    }

    private View getView(final int position) {
        final Comments item = mData.get(position);
        Sender replyUser = item.getSender();
        // 是否有回复
        boolean hasReply = replyUser.isHasReply();

        TextView textView = new TextView(mContext);
        textView.setTextSize(15);
        textView.setTextColor(0xff686868);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        Sender comUser = item.getSender();
        String name = comUser.getUsername();
        if (hasReply) {
            builder.append(setClickableSpan(name, item.getSender()));
            builder.append(" 回复 ");
            builder.append(setClickableSpan(replyUser.getUsername(), item.getSender()));

        } else {
            builder.append(setClickableSpan(name, item.getSender()));
        }
        builder.append(" : ");
        builder.append(setClickableSpanContent(item.getContent(), position));
        textView.setText(builder);
        // 设置点击背景色
        textView.setHighlightColor(getResources().getColor(android.R.color.transparent));
//        textView.setHighlightColor(0xff000000);

        textView.setMovementMethod(new CircleMovement(0xffcccccc, 0xffcccccc));

        textView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(position, item);
            }
        });

        return textView;
    }

    /**
     * 设置评论内容点击事件
     *
     * @param item Item
     * @param position Int
     * @return SpannableString
     */
    public SpannableString setClickableSpanContent(final String item, final int position) {
        final SpannableString string = new SpannableString(item);
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // 评论内容点击事件
                ToastView.showToast("position: " + position + " , content: " + item);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // 设置显示的内容文本颜色
                ds.setColor(0xff686868);
                ds.setUnderlineText(false);
            }
        };
        string.setSpan(span, 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

    /**
     * 设置评论用户名字点击事件
     *
     * @param item Item
     * @param bean Sender
     * @return SpannableString
     */
    public SpannableString setClickableSpan(final String item, final Sender bean) {
        final SpannableString string = new SpannableString(item);
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // 评论用户名字点击事件
                ToastView.showToast(bean.getUsername());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // 设置显示的用户名文本颜色
                ds.setColor(0xff387dcc);
                ds.setUnderlineText(false);
            }
        };

        string.setSpan(span, 0, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

    public interface onItemClickListener {
        void onItemClick(int position, Comments bean);
    }
}

package com.example.wechatmoments.data.bean;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 *     "content": "沙发！",
 *     "images": [{"url": "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRDy7HZaHxn15wWj6pXE4uMKAqHTC_uBgBlIzeeQSj2QaGgUzUmHg"},],
 *     "sender": {"username": "jport","nick": "Joe Portman","avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"},
 *     "comments": [{"content": "Good.","sender": {"username": "outman","nick": "Super hero","avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"}},
 *      error : losted
 *      unknown error : STARCRAFT2
 *
 *
 *   },
 */

public class MomentListBean extends BaseResVo{
    private String content;
    private SenderBean sender;
    @SerializedName(value = "error", alternate = "unknown error")
    private String error;
    private List<ImagesBean> images;
    private List<CommentsBean> comments;

    protected MomentListBean(Parcel in) {
        super(in);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SenderBean getSender() {
        if (sender != null) {
            return sender;
        } else {
            setContent("error = " + getError());
            return new SenderBean();
        }
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }
}

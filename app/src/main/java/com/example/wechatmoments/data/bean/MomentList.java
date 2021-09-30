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

public class MomentList extends BaseResVo{
    private String mContent;
    private Sender mSender;
    @SerializedName(value = "error", alternate = "unknown error")
    private String error;
    private List<Images> mImages;
    private List<Comments> mComments;

    protected MomentList(Parcel in) {
        super();
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Sender getSender() {
        if (mSender != null) {
            return mSender;
        } else {
            setContent("error = " + getError());
            return new Sender();
        }
    }

    public void setSender(Sender mSender) {
        this.mSender = mSender;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    public List<Images> getImages() {
        return mImages;
    }

    public void setImages(List<Images> mImages) {
        this.mImages = mImages;
    }

    public List<Comments> getComments() {
        return mComments;
    }

    public void setComments(List<Comments> mComments) {
        this.mComments = mComments;
    }
}

package com.example.wechatmoments.data.bean;

import android.text.TextUtils;

/**
 * "sender": {
 *       "username": "jport",
 *       "nick": "Joe Portman",
 *       "avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"
 *     }
 */

public class Sender {

    private String mUsername;
    private String mNick;
    private String mAvatar;

    private boolean mHasReply;

    public String getUsername() {
        if (!TextUtils.isEmpty(mUsername)) {
            return mUsername;
        }
        return "errorUserName";
    }

    public boolean isHasReply() {
        return mHasReply;
    }

    public void setHasReply(boolean mHasReply) {
        this.mHasReply = mHasReply;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getNick() {
        if (!TextUtils.isEmpty(mNick)) {
            return mNick;
        }
        return "默认昵称";
    }

    public void setNick(String mNick) {
        this.mNick = mNick;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }
}

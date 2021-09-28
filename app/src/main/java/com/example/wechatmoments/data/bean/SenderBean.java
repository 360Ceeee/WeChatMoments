package com.example.wechatmoments.data.bean;

import android.text.TextUtils;

/**
 * "sender": {
 *       "username": "jport",
 *       "nick": "Joe Portman",
 *       "avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"
 *     }
 */

public class SenderBean {

    private String username;
    private String nick;
    private String avatar;

    private boolean hasReply;

    public String getUsername() {
        if (!TextUtils.isEmpty(username)) {
            return username;
        }
        return "errorUserName";
    }

    public boolean isHasReply() {
        return hasReply;
    }

    public void setHasReply(boolean hasReply) {
        this.hasReply = hasReply;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        if (!TextUtils.isEmpty(nick)) {
            return nick;
        }
        return "默认昵称";
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

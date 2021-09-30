package com.example.wechatmoments.data.bean;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 *
 *   "profile-image": "http://img2.findthebest.com/sites/default/files/688/media/images/Mingle_159902_i0.png",
 *   "avatar": "http://info.thoughtworks.com/rs/thoughtworks2/images/glyph_badge.png",
 *   "nick": "John Smith",
 *   "username": "jsmith"
 *
 */
public class UserInfo extends BaseResVo{

    @SerializedName("profile-image")
    private String profileimage;
    private String avatar;
    private String nick;
    private String username;

    protected UserInfo(Parcel in) {
        super(in);
    }

    public String getProfileimage() {
        return profileimage;
    }

    public void setProfileimage(String profileimage) {
        this.profileimage = profileimage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

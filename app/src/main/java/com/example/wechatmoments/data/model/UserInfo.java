package com.example.wechatmoments.data.model;

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
        super();
    }

    public String getProfileImage() {
        return profileimage;
    }

    public void setProfileImage(String ProfileImage) {
        profileimage = ProfileImage;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String Avatar) {
        avatar = Avatar;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String Nick) {
        nick = Nick;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        username = Username;
    }
}

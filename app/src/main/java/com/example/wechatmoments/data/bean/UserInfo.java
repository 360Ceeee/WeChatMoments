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
    private String mProfileImage;
    private String mAvatar;
    private String mNick;
    private String mUsername;

    protected UserInfo(Parcel in) {
        super();
    }

    public String getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(String ProfileImage) {
        mProfileImage = ProfileImage;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String Avatar) {
        mAvatar = Avatar;
    }

    public String getNick() {
        return mNick;
    }

    public void setNick(String Nick) {
        mNick = Nick;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String Username) {
        mUsername = Username;
    }
}

package com.example.wechatmoments.data.bean;

/**
 * "comments": [
 *       {
 *         "content": "Good.",
 *         "sender": {
 *           "username": "outman",
 *           "nick": "Super hero",
 *           "avatar": "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRJm8UXZ0mYtjv1a48RKkFkdyd4kOWLJB0o_l7GuTS8-q8VF64w"
 *         }
 *       }
 */
public class Comments {
    private String mContent;
    private Sender mSender;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public Sender getSender() {
        return mSender;
    }

    public void setSender(Sender sender) {
        mSender = sender;
    }
}

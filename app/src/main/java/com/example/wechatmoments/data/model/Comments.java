package com.example.wechatmoments.data.model;

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
    private String content;
    private Sender sender;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }
}

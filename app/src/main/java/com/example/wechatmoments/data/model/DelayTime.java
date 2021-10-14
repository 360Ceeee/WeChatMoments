package com.example.wechatmoments.data.model;

public class DelayTime {
    private final int state;
    private Object data;

    public DelayTime(int state, Object data) {
        this.state = state;
        this.data = data;
    }

    public int getState() {
        return state;
    }

}

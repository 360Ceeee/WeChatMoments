package com.example.wechatmoments.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        Constant.ZERO,
        Constant.ONE,
        Constant.MAX_SIZE,
        Constant.MAX_TIME_MILLIS
})

@Retention(RetentionPolicy.SOURCE)

public @interface Constant {
    int ZERO = 0;
    int ONE = 1;
    int MAX_SIZE = 5;
    int MAX_TIME_MILLIS = 2000;
}

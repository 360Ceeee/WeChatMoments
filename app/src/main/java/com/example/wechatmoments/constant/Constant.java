package com.example.wechatmoments.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
        Constant.ZERO,
        Constant.ONE,
        Constant.MAX_SIZE,
        Constant.MAX_TIME_MILLIS,
        Constant.FINISH_SPLASH,
        Constant.NEED_FINISH_SPLASH,
        Constant.NOT_NEED_FINISH_SPLASH
})

@Retention(RetentionPolicy.SOURCE)

public @interface Constant {
    int ZERO = 0;
    int ONE = 1;
    int MAX_SIZE = 5;
    int MAX_TIME_MILLIS = 2000;

    int FINISH_SPLASH = 2;
    int NEED_FINISH_SPLASH = 3;
    int NOT_NEED_FINISH_SPLASH = 4;
}

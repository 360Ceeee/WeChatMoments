package com.example.wechatmoments.utils.http;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private static String TAG = HttpLogger.class.getSimpleName();

    private StringBuilder message = new StringBuilder();

    @Override
    public void log(String message) {
        if (message.startsWith("--> POST")) {
            this.message.setLength(0);
        }
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = Logger.formatJson(message);
        }
        this.message.append(message.concat("\n"));
        if (message.startsWith("<-- END HTTP")) {
            Logger.e(TAG, this.message.toString());
        }
    }
}

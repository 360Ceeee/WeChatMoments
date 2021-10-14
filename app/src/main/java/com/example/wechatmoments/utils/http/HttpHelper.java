package com.example.wechatmoments.utils.http;

import android.content.Context;

import com.example.wechatmoments.utils.ObjectClassUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper {

    private static volatile HttpHelper sHttpHelper = null;

    private static OkHttpClient sOkHttpClient;

    private static Retrofit sRetrofit;

    private static OkHttpClient.Builder sBuilder;

    private static String BASE_URL;

    private HttpHelper() {
    }

    public static HttpHelper getInstance() {
        if (sHttpHelper == null) {
            synchronized (HttpHelper.class) {
                if (sHttpHelper == null) {
                    sHttpHelper = new HttpHelper();
                }
            }
        }
        return sHttpHelper;
    }

    public static void init(Context context, String baseUrl) {
        new Builder(context)
                .initOkHttp()
                .createRetrofit(baseUrl)
                .build();
    }


    public static class Builder {
        private OkHttpClient okHttpClient;

        private OkHttpClient.Builder builder;

        private Retrofit retrofit;

        private final Context context;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * create OkHttp 初始化OKHttpClient,设置缓存,设置超时时间,设置打印日志
         *
         * @return Builder
         */
        public Builder initOkHttp() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLogger());
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            if (builder == null) {
                synchronized (HttpHelper.class) {
                    if (builder == null) {
                        Cache cache = new Cache(new File(context.getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                        builder = new OkHttpClient.Builder()
                                .cache(cache)
                                .addInterceptor(interceptor)
                                .connectTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS)
                                .readTimeout(30, TimeUnit.SECONDS)
                        ;
                    }
                }
            }

            return this;
        }

        /**
         * 添加拦截器
         *
         * @param mInterceptor Interceptor
         * @return Builder
         */
        public Builder addInterceptor(Interceptor mInterceptor) {
            ObjectClassUtil.checkNotNull(mInterceptor);
            this.builder.addNetworkInterceptor(mInterceptor);
            return this;
        }

        /**
         * create retrofit
         *
         * @param baseUrl baseUrl
         * @return Builder
         */
        public Builder createRetrofit(String baseUrl) {
            ObjectClassUtil.checkNotNull(baseUrl);
            Retrofit.Builder builder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl);
            BASE_URL = baseUrl;
            this.okHttpClient = this.builder.build();
            this.retrofit = builder.client(okHttpClient)
                    .build();
            return this;
        }

        public void build() {
            HttpHelper.getInstance().build(this);
        }

    }

    private void build(Builder builder) {
        ObjectClassUtil.checkNotNull(builder);
        ObjectClassUtil.checkNotNull(builder.builder);
        ObjectClassUtil.checkNotNull(builder.okHttpClient);
        ObjectClassUtil.checkNotNull(builder.retrofit);
        sBuilder = builder.builder;
        sOkHttpClient = builder.okHttpClient;
        sRetrofit = builder.retrofit;
    }

    public <T> T create(Class<T> clz) {
        ObjectClassUtil.checkNotNull(clz);
        ObjectClassUtil.checkNotNull(sRetrofit);
        return sRetrofit.create(clz);
    }

}

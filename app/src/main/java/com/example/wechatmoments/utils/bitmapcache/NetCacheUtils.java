package com.example.wechatmoments.utils.bitmapcache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class NetCacheUtils {

    private ImageView ivPic;
    private String url;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils mLocalCacheUtils, MemoryCacheUtils mMemoryCacheUtils) {
        this.mLocalCacheUtils = mLocalCacheUtils;
        this.mMemoryCacheUtils = mMemoryCacheUtils;
    }

    public void getBitmapFromNet(ImageView ivPic, String url) {
        new BitmapTask(this).execute(ivPic, url);
    }

    /**
     * AsyncTask是对Handler和线程池的封装
     * 第一个泛型:参数类型
     * 第二个泛型:更新进度的泛型
     * 第三个泛型:onPostExecute的返回结果
     */
    private static class BitmapTask extends AsyncTask<Object, Void, Bitmap> {
        private WeakReference<NetCacheUtils> mWeakReference;

        public BitmapTask(NetCacheUtils netCacheUtils) {
            mWeakReference = new WeakReference<>(netCacheUtils);
        }

        /**
         * 后台耗时操作 运行在子线程中
         *
         * @param params Object[]
         * @return Bitmap
         */
        @Override
        protected Bitmap doInBackground(Object[] params) {
            NetCacheUtils netCacheUtils = mWeakReference.get();
            netCacheUtils.ivPic = (ImageView) params[0];
            netCacheUtils.url = (String) params[1];
            return downloadBitmap(netCacheUtils.url);
        }

        /**
         * 从网络下载图片
         *
         * @param url url
         * @return Bitmap
         */
        private Bitmap downloadBitmap(String url) {
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");

                int code = conn.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = conn.getInputStream();
                    //压缩图片
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 1;//宽高为原来的1/2
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    return BitmapFactory.decodeStream(inputStream, null, options);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }

        /**
         * 更新进度 主线程
         *
         * @param values values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 耗时方法结束后执行该方法,主线程中
         *
         * @param result Bitmap
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            NetCacheUtils netCacheUtils = mWeakReference.get();
            if (result != null) {
                netCacheUtils.ivPic.setImageBitmap(result);

                //缓存图片到本地
                netCacheUtils.mLocalCacheUtils.setBitmapToLocal(netCacheUtils.url, result);
                // 缓存图片到内存
                netCacheUtils.mMemoryCacheUtils.setBitmapToMemory(netCacheUtils.url, result);


            }
        }
    }


}

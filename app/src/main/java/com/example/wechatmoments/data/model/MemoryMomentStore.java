package com.example.wechatmoments.data.model;

import com.example.wechatmoments.constant.Constant;

import java.util.ArrayList;
import java.util.List;

public class MemoryMomentStore {

    public static int totalPage = Constant.ONE;
    private final List<MomentList> mList = new ArrayList<>();

    private static MemoryMomentStore instance;

    private MemoryMomentStore() {

    }

    public static MemoryMomentStore getInstance() {
        if (instance == null) {
            synchronized (MemoryMomentStore.class) {
                if (instance == null) {
                    instance = new MemoryMomentStore();
                }
            }
        }
        return instance;
    }

    public synchronized void saveMomentList(List<MomentList> list) {
        mList.clear();
        mList.addAll(list);
    }

    /**
     * 获取5条朋友圈列表数据
     * 当：
     * page = 1  ------  0 - 4， 对应0, 5
     * page = 2  ------  5 - 9， 对应5, 10
     * page = 3  ------  10 - 14， 对应10, 15
     *
     * 所以：
     * start = (page - 1) * 5
     * end = page * 5
     *
     * @param page Int
     * @return List<MomentList>
     */
    public synchronized List<MomentList> getPartMomentList(int page) {
        int start = (page - Constant.ONE) * Constant.MAX_SIZE;
        int end = page * Constant.MAX_SIZE;
        int size = mList.size();
        if (size >= (end - Constant.ONE)) {
            return mList.subList(start, end);
        } else {
            return mList.subList(start, size);
        }
    }

}

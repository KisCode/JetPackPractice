package com.kiscode.paging.model.mock;


import com.kiscode.paging.model.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/****
 * Description:  模拟网络请求
 * Author:  keno
 * CreateDate: 2021/4/8 21:03
 */

public class MockApi {
    private static final int PAGE_SIZE = 20;


    public static List<User> loadUserList(int page) {
        //随机耗时 500~3500ms
        long time = (long) (Math.random() * 3000 + 200);
        try {
            //模拟耗时
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= PAGE_SIZE; i++) {
            User user = new User();
            user.setNumber(PAGE_SIZE * (page - 1) + i);
            user.setId(UUID.randomUUID().toString());
            userList.add(user);
        }
        return userList;
    }

    public static void loadUserList(int page, OnLoadListener loadListener) {
        //随机耗时 500~3500ms
        long time = (long) (Math.random() * 3000 + 200);

        try {
            //模拟耗时
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (time % 3 == 0) {
            loadListener.onFailed(new Exception("加载数据失败了..."));
            return;
        }

        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= PAGE_SIZE; i++) {
            User user = new User();
            user.setNumber(PAGE_SIZE * (page - 1) + i);
            user.setId(UUID.randomUUID().toString());
            userList.add(user);
        }


        loadListener.onLoad(userList);
    }

    public interface OnLoadListener {
        void onLoad(List<User> userList);

        void onFailed(Throwable throwable);
    }
}

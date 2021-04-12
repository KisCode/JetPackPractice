package com.kiscode.paging.model.mock;


import com.kiscode.paging.model.pojo.Article;
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

    private static List<User> getUserList(int pageIndex, int pageSize) {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= pageSize; i++) {
            User user = new User();
            user.setNumber(pageSize * (pageIndex - 1) + i);
            user.setId(UUID.randomUUID().toString());
            userList.add(user);
        }
        return userList;
    }

    public static void loadUserList(int pageIndex, int pageSize, OnLoadListener loadListener) {
        //随机耗时 500~3500ms
        long time = (long) (Math.random() * 3000 + 200);

        try {
            //模拟耗时
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (pageIndex > 1 && time % 3 == 0) {
            loadListener.onFailed(new Exception("加载数据失败了..."));
            return;
        }

        List<User> userList = getUserList(pageIndex, pageSize);


        loadListener.onLoad(userList);
    }

    public static void loadArticleList(int pageIndex, int pageSize, String searchTitle, OnLoadArticleListener loadListener) {
        //随机耗时 300~2300ms
        long time = (long) (Math.random() * 2000 + 300);

        try {
            //模拟耗时
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*
        if (pageIndex != 1 && time % 3 == 0) {
            loadListener.onFailed(new Exception("加载数据失败了..."));
            return;
        }*/

        List<Article> articleList = new ArrayList<>();
        for (int i = 1; i <= pageSize; i++) {
            int id = pageSize * (pageIndex - 1) + i;
            String titleStr = searchTitle + "_" + UUID.randomUUID().toString();
            articleList.add(new Article(id, titleStr));
        }

        loadListener.onLoad(articleList);
    }


    public interface OnLoadListener {
        void onLoad(List<User> userList);

        void onFailed(Throwable throwable);
    }

    public interface OnLoadArticleListener {
        void onLoad(List<Article> articleList);

        void onFailed(Throwable throwable);
    }
}

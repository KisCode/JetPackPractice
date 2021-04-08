package com.kiscode.paging.model.datasoure;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.kiscode.paging.model.mock.MockApi;
import com.kiscode.paging.model.pojo.User;

import java.util.List;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:22
 */

public class UserDataSoure extends PageKeyedDataSource<Integer, User> {
    //初始页码
    private static final int PAGE_INNITIAL = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, User> callback) {
        //初始化
        List<User> userList = MockApi.loadUserList(PAGE_INNITIAL);
        callback.onResult(userList, null, PAGE_INNITIAL + 1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, User> callback) {
        //加载前一页
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, User> callback) {
        //加载后一页
        List<User> userList = MockApi.loadUserList(params.key);
        callback.onResult(userList, params.key + 1);
    }
}

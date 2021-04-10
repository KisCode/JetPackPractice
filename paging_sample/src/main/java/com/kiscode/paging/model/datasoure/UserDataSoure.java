package com.kiscode.paging.model.datasoure;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.kiscode.paging.comman.LoadStatus;
import com.kiscode.paging.model.mock.MockApi;
import com.kiscode.paging.model.pojo.User;

import java.util.List;
import java.util.function.Function;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:22
 */

public class UserDataSoure extends PageKeyedDataSource<Integer, User> {
    //初始页码
    private static final int PAGE_INNITIAL = 1;
    private static final int PAGE_SIZE = 10;

    public MutableLiveData<LoadStatus> loadStatusLiveData = new MutableLiveData<>();

    public UserDataSoure() {
        Log.i("UserDataSoure", "UserDataSoure");
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, User> callback) {
        //初始化
        List<User> userList = MockApi.loadUserList(PAGE_INNITIAL, PAGE_SIZE);
        callback.onResult(userList, null, PAGE_INNITIAL + 1);

/*        MockApi.loadUserList(PAGE_INNITIAL, new MockApi.OnLoadListener() {
            @Override
            public void onLoad(List<User> userList) {
                callback.onResult(userList, null, PAGE_INNITIAL + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {
                loadStatusLiveData.postValue(LoadStatus.FAILED);
            }
        });*/

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
/*
        List<User> userList = MockApi.loadUserList(params.key);
        callback.onResult(userList, params.key + 1);
*/

        MockApi.loadUserList(params.key, PAGE_SIZE, new MockApi.OnLoadListener() {
            @Override
            public void onLoad(List<User> userList) {
                callback.onResult(userList, params.key + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {
                Log.i("onFailed", throwable.getMessage());
                loadStatusLiveData.postValue(LoadStatus.FAILED);
            }
        });
    }

    public void resetQuery() {
        invalidate();
    }

}

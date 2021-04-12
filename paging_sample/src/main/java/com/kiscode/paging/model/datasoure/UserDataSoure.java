package com.kiscode.paging.model.datasoure;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.core.util.Consumer;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.kiscode.paging.comman.LoadStatus;
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
    private static final int PAGE_SIZE = 10;

    public MutableLiveData<LoadStatus> loadStatusLiveData = new MutableLiveData<>();

    public UserDataSoure() {
        Log.i("UserDataSoure", "UserDataSoure");
    }

    //重试函数
    public Consumer<Void> retryConsumer;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, User> callback) {
        retryConsumer = null;
        loadStatusLiveData.postValue(LoadStatus.INITAL_LOADING);

        //初始化
        MockApi.loadUserList(PAGE_INNITIAL, PAGE_SIZE, new MockApi.OnLoadListener() {
            @Override
            public void onLoad(List<User> userList) {
                loadStatusLiveData.postValue(LoadStatus.COMPLETE);
                callback.onResult(userList, null, PAGE_INNITIAL + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {
                loadStatusLiveData.postValue(LoadStatus.FAILED);
                retryConsumer = (Void v) -> loadInitial(params, callback);
            }
        });

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
        Log.i("loadAfter", Thread.currentThread().getName() + "\tretry : " + (retryConsumer != null));
        retryConsumer = null;
        loadStatusLiveData.postValue(LoadStatus.LOADING);

        MockApi.loadUserList(params.key, PAGE_SIZE, new MockApi.OnLoadListener() {
            @Override
            public void onLoad(List<User> userList) {
                loadStatusLiveData.postValue(LoadStatus.COMPLETE);
                callback.onResult(userList, params.key + 1);
            }

            @Override
            public void onFailed(Throwable throwable) {
                retryConsumer = (Void v) -> loadAfter(params, callback);

                Log.i("onFailed", throwable.getMessage());
                loadStatusLiveData.postValue(LoadStatus.FAILED);

            }
        });
    }

    public void resetQuery() {
        invalidate();
    }

    @SuppressLint("RestrictedApi")
    public void retry() {
        if (retryConsumer == null) return;
        ArchTaskExecutor.getInstance().executeOnDiskIO(() -> retryConsumer.accept(null));
    }

}

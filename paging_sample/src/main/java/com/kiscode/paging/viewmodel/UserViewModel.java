package com.kiscode.paging.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kiscode.paging.comman.LoadStatus;
import com.kiscode.paging.model.datasoure.UserDataSoureFactory;
import com.kiscode.paging.model.pojo.User;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:32
 */

public class UserViewModel extends ViewModel {
    private final UserDataSoureFactory factory;
    public final LiveData<PagedList<User>> pagedListLiveData;
    public final LiveData<LoadStatus> loadStatusLiveData;

    public UserViewModel() {
        factory = new UserDataSoureFactory();
        pagedListLiveData = new LivePagedListBuilder<>(factory, 100).build();
//        loadStatusLiveData = Transformations.switchMap(factory.userDataSoureLiveData, input -> input.loadStatusLiveData);
        loadStatusLiveData = factory.userDataSoureLiveData;
    }


    public void resetQuery() {
        //刷新数据
        pagedListLiveData.getValue().getDataSource().invalidate();
    }
}

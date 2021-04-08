package com.kiscode.paging.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kiscode.paging.model.datasoure.UserDataSoureFactory;
import com.kiscode.paging.model.pojo.User;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:32
 */

public class UserViewModel extends ViewModel {
    public LiveData<PagedList<User>> pagedListLiveData = new LivePagedListBuilder<>(new UserDataSoureFactory(), 100).build();


    public void resetQuery() {
        //刷新数据
        pagedListLiveData.getValue().getDataSource().invalidate();
    }
}

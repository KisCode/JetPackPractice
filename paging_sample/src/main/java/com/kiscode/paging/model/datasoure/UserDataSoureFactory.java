package com.kiscode.paging.model.datasoure;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.kiscode.paging.model.pojo.User;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:30
 */

public class UserDataSoureFactory extends DataSource.Factory<Integer, User> {
    @NonNull
    @Override
    public DataSource<Integer, User> create() {
        return new UserDataSoure();
    }
}

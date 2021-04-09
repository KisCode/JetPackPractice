package com.kiscode.paging.model.datasoure;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.kiscode.paging.model.pojo.User;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 21:30
 */

public class UserDataSoureFactory extends DataSource.Factory<Integer, User> {
    public MutableLiveData<UserDataSoure> userDataSoureLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, User> create() {
        UserDataSoure userDataSoure = new UserDataSoure();
        userDataSoureLiveData.postValue(userDataSoure);
        return userDataSoure;
    }
}

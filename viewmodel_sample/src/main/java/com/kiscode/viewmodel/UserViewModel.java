package com.kiscode.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kiscode.viewmodel.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/15 11:37
 **/
//public class UserViewModel extends ViewModel {
public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<List<User>> users;

    public UserViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("UserViewModel","onCleared");
    }

    public MutableLiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    public void refresh() {
        Log.i("refrsh", "refrsh:" + getApplication());
        loadUsers();
    }

    private void loadUsers() {
        //异步加载用户列表
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = new ArrayList<>();
                Random random = new Random();
                int startPos = random.nextInt(10);
                for (int i = startPos; i < startPos + 20; i++) {
                    try {
                        Thread.sleep(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    User user = new User();
                    user.setName("User_" + i);
                    user.setAge(random.nextInt(100));
                    userList.add(user);
                }
                users.postValue(userList);
            }
        }).start();
    }
}
package com.keno.databinding.sample.pojo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/**
 * Description: 登录信息
 * Author: keno
 * CreateDate: 2020/3/4 21:31
 */

public class LoginModel extends BaseObservable {
    public String account;
    public String password;

    public LoginModel(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Bindable
    public String getAccount() {
        return account;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}

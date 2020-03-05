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
    //非 public修饰属性 @Bindable只能作用在geter方法上
    private String account;
    private String password;

//    @Bindable //public修饰属性可以直接用 @Bindable注解
    private float randomNum;

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

    @Bindable
    public float getRandomNum() {
        return randomNum;
    }

    public void setAccount(String account) {
        this.account = account;
        //仅刷新指定字段
        notifyPropertyChanged(BR.account);
    }

    public void setPassword(String password) {
        this.password = password;
//        notifyPropertyChanged(BR.password);
        //刷新全部字段
        notifyChange();
    }

    public void setRandomNum(float randomNum) {
        this.randomNum = randomNum;
    }
}

package com.keno.databinding.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.util.Log;

import com.keno.databinding.sample.databinding.ActivitySingleBindingBinding;
import com.keno.databinding.sample.pojo.LoginModel;

import java.util.Random;

/**
 * Description: 数据绑定实例
 * Author: keno
 * CreateDate: 2020/3/5 21:26
 */

public class SingleBindingActivity extends AppCompatActivity {

    private ActivitySingleBindingBinding binding;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginModel = new LoginModel("name1", "pwd1");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_binding);

        //绑定事件持有类
        binding.setEventPresenter(new EventPresenter());

        //绑定data
        binding.setLoginModel(loginModel);

        //实现BaseObservable 的类可进行数据变化监听
        loginModel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(propertyId ==BR.account){
                    Log.i("Changed","PropertyChange account");
                }else if(propertyId ==BR.password){
                    Log.i("Changed","PropertyChange password");
                }else if(propertyId ==BR.randomNum){
                    Log.i("Changed","PropertyChange randomNum");
                }else {
                    Log.i("Changed","PropertyChange unknow");
                }
            }
        });
    }

    public class EventPresenter {
        public void changeAccout() {
            loginModel.setRandomNum(new Random().nextFloat());
            loginModel.setAccount("acount:" + new Random().nextInt());
        }

        public void changePassword() {
            loginModel.setRandomNum(new Random().nextFloat());
            loginModel.setPassword("password:" + new Random().nextInt());
        }
    }
}

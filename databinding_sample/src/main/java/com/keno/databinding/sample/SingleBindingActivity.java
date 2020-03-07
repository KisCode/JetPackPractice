package com.keno.databinding.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import android.os.Bundle;
import android.util.Log;

import com.keno.databinding.sample.databinding.ActivitySingleBindingBinding;
import com.keno.databinding.sample.pojo.LoginModel;
import com.keno.databinding.sample.pojo.UserFieldModel;

import java.util.Random;

/**
 * Description: 数据绑定实例
 * Author: keno
 * CreateDate: 2020/3/5 21:26
 */

public class SingleBindingActivity extends AppCompatActivity {
    private ActivitySingleBindingBinding binding;
    private LoginModel loginModel;
    private UserFieldModel userFieldModel;

    private ObservableList<Integer> observableList = new ObservableArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginModel = new LoginModel("name1", "pwd1");
        userFieldModel =new UserFieldModel();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_binding);

        //绑定事件持有类
        binding.setEventPresenter(new EventPresenter());

        //绑定data 继承自BaseObservable
        binding.setLoginModel(loginModel);

        //绑定 BaseObservableField字段的对象
        binding.setUserModel(userFieldModel);

        binding.setObservableList(observableList);

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

        public void changeUserField() {
            userFieldModel.age.set(new Random().nextInt());
            userFieldModel.name.set("Jordan");
        }

        public void changeList() {
            observableList.add(new Random().nextInt());
        }
    }
}

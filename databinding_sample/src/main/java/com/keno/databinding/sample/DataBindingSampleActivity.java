package com.keno.databinding.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.keno.databinding.sample.databinding.ActivityDataBindingSampleBinding;
import com.keno.databinding.sample.pojo.LoginModel;

public class DataBindingSampleActivity extends AppCompatActivity {

    private ActivityDataBindingSampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_sample);
        LoginModel loginModel = new LoginModel("account1", "password1");
        binding.setLoginModel(loginModel);

    }
}

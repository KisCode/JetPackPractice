package com.keno.databinding.sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.keno.databinding.sample.databinding.ActivityEventBindingBinding;
import com.keno.databinding.sample.pojo.LoginModel;

/**
 * Description: 事件绑定
 * Author: Kris Keno
 * Date : 2020/3/7 9:38 AM
 **/
public class EventBindingActivity extends AppCompatActivity {

    private ActivityEventBindingBinding binding;
    private LoginModel loginModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_binding);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_binding);

        loginModel = new LoginModel("WANG", "zxc@23");

        binding.setLoginModel(loginModel);
        binding.setEventPresent(new EventPresenter());
        binding.setTextWatcher(new EditTextWatch());
    }

    public class EventPresenter {
        public void showUserName(LoginModel loginModel) {
            // @{()->eventPresent.showUserName(loginModel)}
            Toast.makeText(EventBindingActivity.this, loginModel.getAccount(), Toast.LENGTH_SHORT).show();
        }

        public void onClick(View view) {
            //可以选择保持事件回调方法的签名一致：@{eventPresent.onClick}
//            Log.i("afterTextChanged",s.toString());
        }
    }

    public class EditTextWatch implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.i("afterTextChanged",editable.toString());
        }
    }
}

package com.kiscode.jetpack.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kiscode.jetpack.viewmodel.vm.NumberViewModel;
import com.kiscode.viewmodel.R;

/**
 * Description: ViewModel用法演示 （不包含DataBinding）
 * ViewModel的生命周期 是长于Activty的，当设备旋转时会销毁当前页面onDestory 进行重新创建oncreate,ViewModel依然可以完整保存数据
 * Author: keno
 * CreateDate: 2020/6/24 10:36
 */

public class ViewModelUsageActivity extends AppCompatActivity {
    private static final String TAG = "ViewModelUsageActivity";
    private NumberViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_usage);
        Log.i(TAG, "oncreate");
        viewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(NumberViewModel.class);

        final TextView tvNumber = findViewById(R.id.tvNumber);
        tvNumber.setText(String.valueOf(viewModel.getNumber().getValue()));
        tvNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.add();  //nuber自增
                tvNumber.setText(String.valueOf(viewModel.getNumber().getValue()));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
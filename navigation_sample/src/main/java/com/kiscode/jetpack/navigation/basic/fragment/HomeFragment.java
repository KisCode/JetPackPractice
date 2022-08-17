package com.kiscode.jetpack.navigation.basic.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.common.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Navigation 基础用法，不同页面跳转演示
 */
public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected int getLayoutRes() {
        Log.i(TAG, "getLayoutRes");
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        Log.i(TAG, "initData");
    }

    @Override
    protected void initViews(View view) {
        Log.i(TAG, "initViews");
        TextView tvTime = view.findViewById(R.id.tv_time);
        tvTime.setText("页面创建时间:\t" + simpleDateFormat.format(Calendar.getInstance().getTime()));
        Button button = view.findViewById(R.id.btn_to_detail);
        final EditText etName = view.findViewById(R.id.et_name);
        final NavController navController = Navigation.findNavController(view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "请输入名称", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("name", name);
                    navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}

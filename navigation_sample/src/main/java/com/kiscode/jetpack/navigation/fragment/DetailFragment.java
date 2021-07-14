package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.kiscode.jetpack.navigation.R;
import com.kiscode.jetpack.navigation.fragment.strategy.AnimationEndLoadStrategy;

/**
 * Description: 详情页面
 * Author: keno
 * Date : 2021/7/14 9:58
 **/
public class DetailFragment extends BaseFragment {

    private static final String TAG = "DetailFragment";

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载完成再加载页面策略
        setInitLoadStrategy(new AnimationEndLoadStrategy(getActivity(),this));
    }

    @Override
    protected void initViews(View view) {
        Log.d(TAG, "initViews");
        final String name = getArguments().getString("name");
        try {
            //模拟耗时操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final NavController navController = Navigation.findNavController(view);
        Button button = view.findViewById(R.id.btn_to_home);
        TextView tvName = view.findViewById(R.id.tv_name);
        tvName.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_detailFragment_to_homeFragment);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String name = getArguments().getString("name");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(name);
    }
}

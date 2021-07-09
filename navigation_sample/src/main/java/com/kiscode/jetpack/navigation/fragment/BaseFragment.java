package com.kiscode.jetpack.navigation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description: Fragment 基类
 * 由于Navigation统一管理fragment声明周期，存在返回上个页面fragment会重建
 * 1. 启动页面A --> 自动B ,此时页面A的 生命周期为onPause() --> onStop()
 * 2. 从页面B返回 页面A, 时页面A的生命周期会执行 onCreateView() --> onViewCreated()，导致页面A重复创建
 * <p>
 * 解决方案：在BaseFragment中缓存已加载根布局，页面返回时不再重复加载
 * Author: keno
 * Date : 2021/7/9 14:56
 **/
public abstract class BaseFragment extends Fragment {
    private boolean mIsFirsInit = true;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirsInit) {
            mIsFirsInit = false;
            initViews(view);
            initData();
        }
    }

    /***
     * fragment布局资源文件
     * @return 布局资源id
     */
    protected abstract @LayoutRes
    int getLayoutRes();

    /**
     * 初始化数据，仅在第一次onViewCreated生命周期方法触发时调用
     */
    protected abstract void initData();

    /**
     * 初始化view, 仅在第一次onCreateView声明周期方法触发时调用
     *
     * @param view 根布局文件
     */
    protected abstract void initViews(View view);
}
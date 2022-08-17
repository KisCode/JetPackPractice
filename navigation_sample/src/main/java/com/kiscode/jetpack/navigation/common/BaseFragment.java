package com.kiscode.jetpack.navigation.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kiscode.jetpack.navigation.common.strategy.DefaultInitLoadStrategy;
import com.kiscode.jetpack.navigation.common.strategy.IInitLoad;
import com.kiscode.jetpack.navigation.common.strategy.AbsInitLoadStrategy;

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
public abstract class BaseFragment extends Fragment implements IInitLoad {
    private static final String TAG = "BaseFragment";
    private AbsInitLoadStrategy initLoadStrategy;
    private boolean mIsFirsInit = true;
    private View mRootView;

    //阅读fragment源码发现onCreateAnimation在ACTIVITY_CREATED周期方法回调
    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if (initLoadStrategy == null) {
            initLoadStrategy = new DefaultInitLoadStrategy(getActivity(), this);
        }
        Animation animation = initLoadStrategy.load(enter, nextAnim);
        return animation != null ? animation : super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutRes(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
    }

    @Override
    public void init() {
        Log.i(TAG, "init " + getClass().getSimpleName() + " mIsFirsInit:" + mIsFirsInit);
        if (mRootView == null) return;

        if (mIsFirsInit) {
            mIsFirsInit = false;
            initViews(mRootView);
            initData();
        }
    }

    public void setInitLoadStrategy(AbsInitLoadStrategy initLoadStrategy) {
        this.initLoadStrategy = initLoadStrategy;
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
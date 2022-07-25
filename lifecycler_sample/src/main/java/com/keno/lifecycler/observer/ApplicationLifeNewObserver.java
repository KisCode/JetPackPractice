package com.keno.lifecycler.observer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * Description: 应用进程声明周期监听
 * Author: keno
 * Date : 2022/7/22 16:35
 **/
public class ApplicationLifeNewObserver implements DefaultLifecycleObserver {
    private static final String TAG = "NewLifeObserver";
    private Context context;

    public ApplicationLifeNewObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStart(owner);
        Log.i(TAG, "onForeground");
        Toast.makeText(context, "应用回到前台", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onStop(owner);
        Log.i(TAG, "onBackground");
        Toast.makeText(context, "应用退到后台", Toast.LENGTH_SHORT).show();
    }
}
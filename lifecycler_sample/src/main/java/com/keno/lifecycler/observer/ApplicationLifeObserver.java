package com.keno.lifecycler.observer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Description: 应用进程声明周期监听
 * Author: keno
 * Date : 2022/7/22 16:35
 **/
public class ApplicationLifeObserver implements LifecycleObserver {
    private static final String TAG = "LifeObserver";
    private Context context;

    public ApplicationLifeObserver(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onForeground() {
        Log.i(TAG, "onForeground");
        Toast.makeText(context, "应用回到前台", Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground() {
        Log.i(TAG, "onBackground");
        Toast.makeText(context, "应用退到后台", Toast.LENGTH_SHORT).show();
    }
}
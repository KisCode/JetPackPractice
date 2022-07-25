package com.keno.lifecycler;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.keno.lifecycler.observer.ApplicationLifeNewObserver;
import com.keno.lifecycler.observer.ApplicationLifeObserver;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //监听整个应用的生命周期
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationLifeObserver(this));

        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationLifeNewObserver(this));
    }

}
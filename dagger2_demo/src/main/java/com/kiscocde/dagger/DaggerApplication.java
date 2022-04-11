package com.kiscocde.dagger;

import android.app.Application;
import android.util.Log;

import com.kiscocde.dagger.component.DaggerOkHttpComponent;

import javax.inject.Inject;

import okhttp3.OkHttpClient;


public class DaggerApplication extends Application {
    private static final String TAG = "DaggerApplication";

    @Inject
    OkHttpClient okHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerOkHttpComponent.create().inject(this);
        Log.i(TAG, "onCreate" + okHttpClient);
    }
}
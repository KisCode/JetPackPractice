package com.keno.databinding.sample;


import android.app.Application;
import android.content.Context;


public class DataBindingApp extends Application {

    public static DataBindingApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}

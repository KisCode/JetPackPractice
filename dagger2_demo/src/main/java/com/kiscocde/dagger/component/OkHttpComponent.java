package com.kiscocde.dagger.component;

import android.app.Application;

import com.kiscocde.dagger.module.OkHttpModule;

import javax.inject.Singleton;

import dagger.Component;

//@Singleton
@Component(modules = OkHttpModule.class)
public interface OkHttpComponent {
    void inject(Application application);
} 
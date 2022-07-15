package com.kiscocde.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class OkHttpModule {
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }
} 
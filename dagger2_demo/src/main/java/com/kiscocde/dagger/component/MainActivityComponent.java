package com.kiscocde.dagger.component;

import com.kiscocde.dagger.MainActivity;

import dagger.Component;

@Component
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
} 
package com.kiscocde.dagger.pojo;

import android.util.Log;

import javax.inject.Inject;

public class Student {

    @Inject
    public Student() {
    }

    public void hello() {
        Log.i("Student", "hello,Student");
    }
} 
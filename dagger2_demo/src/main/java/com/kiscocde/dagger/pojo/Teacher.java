package com.kiscocde.dagger.pojo;

import android.app.Activity;
import android.widget.Toast;

import javax.inject.Inject;

public class Teacher {
    Student student;

    @Inject
    public Teacher(Student student) {
        this.student = student;
    }

    public void show(Activity activity) {
        Toast.makeText(activity, "Hello,I'm teacher", Toast.LENGTH_SHORT).show();
    }

    public void helloStudent() {
        student.hello();
    }
}
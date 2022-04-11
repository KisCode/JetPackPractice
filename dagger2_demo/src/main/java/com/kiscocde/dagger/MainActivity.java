package com.kiscocde.dagger;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kiscocde.dagger.component.DaggerMainActivityComponent;
import com.kiscocde.dagger.pojo.Student;
import com.kiscocde.dagger.pojo.Teacher;

import javax.inject.Inject;



public class MainActivity extends AppCompatActivity {

    //构造函数注入
    @Inject
    Student student;

    @Inject
    Teacher teacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.create().inject(this);
//        student.hello();

        teacher.show(this);
        teacher.helloStudent();


    }
}
package com.kiscode.jetpack.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startBasicSample(View view) {
        startActivity(new Intent(this, NavBasicActivity.class));
    }

    public void startViewModelSample(View view) {
        startActivity(new Intent(this, NavViewModelActivity.class));
    }

    public void startAppBarSample(View view) {
        startActivity(new Intent(this, NavAppBarActivity.class));
    }
}
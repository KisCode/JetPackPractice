package com.kiscode.jetpack.navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/***
 * 结合ViewModel使用Navigation
 */
public class NavViewModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view_model);
    }
}

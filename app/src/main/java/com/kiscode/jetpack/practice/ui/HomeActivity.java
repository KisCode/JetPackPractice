package com.kiscode.jetpack.practice.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.kiscode.jetpack.practice.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //设置ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        //NavHostFragment
/*
        fragment容器通过Navigation.findNavController 检索NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_home);
        NavigationUI.setupActionBarWithNavController(this, navController);
        */

        // FragmentContainerView容器通过 getSupportFragmentManager().findFragmentById 检索NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container_home);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_home);
        //返回上级目录
        return navController.navigateUp();
    }
}

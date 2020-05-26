package com.kiscode.jetpack.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

/**
 * Description:
 * Author: kisCode
 * Date : 2020/5/25 10:29
 **/
public class NavigationHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);

        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //返回上级目录
        return navController.navigateUp();
    }
}

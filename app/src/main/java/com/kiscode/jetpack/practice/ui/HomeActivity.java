package com.kiscode.jetpack.practice.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.kiscode.jetpack.practice.R;
import com.kiscode.jetpack.practice.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityHomeBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        //设置ActionBar
        setSupportActionBar(binding.toolbarHome);

        //NavHostFragment
/*
        fragment容器通过Navigation.findNavController 检索NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_home);
        NavigationUI.setupActionBarWithNavController(this, navController);
        */

        // FragmentContainerView容器通过 getSupportFragmentManager().findFragmentById 检索NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container_home);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
//        NavigationUI.setupActionBarWithNavController(this, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(binding.drawerlayoutHome).build();


        NavigationUI.setupActionBarWithNavController(this, navController, this.appBarConfiguration);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up navigation menu
        NavigationUI.setupWithNavController(binding.navigationView, navController);

    }


    @Override
    public boolean onSupportNavigateUp() {
/*
        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container_home);
        //返回上级目录
        return navController.navigateUp();
*/
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerlayoutHome.isDrawerOpen(GravityCompat.START)) {
            binding.drawerlayoutHome.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

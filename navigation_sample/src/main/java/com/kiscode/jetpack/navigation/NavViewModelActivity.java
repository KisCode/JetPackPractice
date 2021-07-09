package com.kiscode.jetpack.navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

/***
 * 结合ViewModel使用Navigation
 */
public class NavViewModelActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_view_model);

        Toolbar toolbar = findViewById(R.id.toolbar_vm);
        setSupportActionBar(toolbar);

        //NavHostFragment
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_vm);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //返回上级目录
        return navController.navigateUp();
    }
}

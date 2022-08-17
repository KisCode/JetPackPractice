package com.kiscode.jetpack.navigation.basic;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.kiscode.jetpack.navigation.R;

/**
 * Description:
 * Author: kisCode
 * Date : 2020/5/25 10:29
 **/
public class NavBasicActivity extends AppCompatActivity {
    public Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);

        toolbar = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);

        /*navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if (destination.getId() == R.id.detailFragment) {
                    Log.i("destination", destination.getLabel().toString());
                    toolbar.setTitle("New "+destination.getLabel());
                }
            }
        });*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        //NavHostFragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //返回上级目录
        return navController.navigateUp();
    }
}

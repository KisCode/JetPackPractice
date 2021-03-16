package com.kiscode.viewmodel;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.kiscode.viewmodel.adapter.UserAdapter;
import com.kiscode.viewmodel.pojo.User;

import java.util.Collections;
import java.util.List;

public class ViewModelHomeActivity extends AppCompatActivity {
    private static final String TAG = "ViewmodelHomeActivity";
    UserViewModel userViewModel;
    private UserAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmodel_home);
        Log.i(TAG, "onCreate");
        initViewModel();
        initView();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    private void initView() {
        refreshLayout = findViewById(R.id.swiperefresh_home);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userViewModel.refresh();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_home);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new UserAdapter(Collections.emptyList());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initViewModel() {
//        userViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        userViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserViewModel.class);
    }

    private void loadData() {
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                Log.d(TAG, "onChanged in " + Thread.currentThread().getName());
                refreshLayout.setRefreshing(false);
                for (int i = 0; i < users.size(); i++) {
                    Log.i(TAG, users.toString());
                }
                mAdapter.setNewDatas(users);
            }
        });
    }
}
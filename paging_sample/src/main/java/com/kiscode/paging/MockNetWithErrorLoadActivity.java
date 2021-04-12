package com.kiscode.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kiscode.paging.adapter.UserAdapter;
import com.kiscode.paging.comman.LoadStatus;
import com.kiscode.paging.model.pojo.User;
import com.kiscode.paging.viewmodel.UserViewModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MockNetWithErrorLoadActivity extends BaseActionBarActivity {

    private UserViewModel viewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_remote);

        getSupportActionBar().setTitle(R.string.title_mock_net_with_error);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);

        viewModel.pagedListLiveData.observe(this, users -> {
            swipeRefreshLayout.setRefreshing(false);
            adapter.submitList(users);
        });

        viewModel.loadStatusLiveData.observe(this, loadStatus -> {
            Log.i("loadResult", loadStatus.name());
            adapter.updateLoadStatus(loadStatus);
        });

        swipeRefreshLayout = findViewById(R.id.swiperefresh_remote);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.resetQuery();
        });

        recyclerView = findViewById(R.id.recyclerview_remote);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnRetryListener(new UserAdapter.OnRetryListener() {
            @Override
            public void onRetry() {
                viewModel.retry();
            }
        });

    }
}
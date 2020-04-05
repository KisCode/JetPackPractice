package com.keno.databinding.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keno.databinding.sample.adapter.ItemAdapter;
import com.keno.databinding.sample.adapter.ItemBindAdapter;
import com.keno.databinding.sample.pojo.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BindRecyclerviewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAdd;
    private RecyclerView recyclerView;
//    ActivityBindRecyclerviewBinding binding;

    private List<UserInfo> mUserList;
//    private ItemAdapter mAdapter;
    private ItemBindAdapter itemBindAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_recyclerview);
        initView();
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_recyclerview);
    }

    private void initView() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnAdd.setOnClickListener(this);

        mUserList = new ArrayList<>();
//        mAdapter =new ItemAdapter(this, mUserList);

        itemBindAdapter =new ItemBindAdapter(this,mUserList);

        recyclerView.setAdapter(itemBindAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                int randomNum = new Random().nextInt();
                UserInfo userInfo =new UserInfo("Name："+randomNum,"email:"+randomNum);
                itemBindAdapter.add(userInfo);
                break;
        }
    }
}

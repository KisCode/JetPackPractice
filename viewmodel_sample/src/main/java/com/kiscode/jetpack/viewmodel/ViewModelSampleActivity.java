package com.kiscode.jetpack.viewmodel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewModelSampleActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUsage;
    private Button btnShare;
    private Button btnSavestate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btnUsage = findViewById(R.id.btn_usage);
        btnShare = findViewById(R.id.btn_share);
        btnSavestate = findViewById(R.id.btn_savestate);

        btnUsage.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnSavestate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_usage:
                startActivity(new Intent(this, ViewModelUsageActivity.class));
                break;
            case R.id.btn_share:
                startActivity(new Intent(this, ShareDataActivity.class));
                break;
            case R.id.btn_savestate:

                break;
        }
    }
}

package com.kiscode.paging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startRoomSample(View view) {
        startActivity(new Intent(this, RoomLoadActivity.class));
    }

    public void startMockNetRequest(View view) {
        startActivity(new Intent(this, RemoteLoadActivity.class));
    }

    public void startMockNetRequestWithParam(View view) {
        startActivity(new Intent(this, SearchArticleActivity.class));
    }
}
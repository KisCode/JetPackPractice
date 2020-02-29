package com.kiscode.jetpack.room;

import android.app.Application;

import com.kiscode.jetpack.room.db.AppDatabase;

import androidx.room.Room;

/**
 * Description: Application
 * Author: Kris Keno
 * Date : 2020/2/29 9:57 AM
 **/
public class RoomSampleApp extends Application {
    private AppDatabase roomDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        //创建数据库
        roomDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_sample_dev.db")
                .build();
    }

    public AppDatabase getRoomDatabase() {
        return roomDatabase;
    }
}

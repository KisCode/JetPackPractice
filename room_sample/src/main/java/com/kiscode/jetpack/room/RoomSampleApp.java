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

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase.init(this);
    }

}

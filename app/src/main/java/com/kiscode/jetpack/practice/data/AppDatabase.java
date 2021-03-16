package com.kiscode.jetpack.practice.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.kiscode.jetpack.practice.data.pojo.Plant;
import com.kiscode.jetpack.practice.workers.SeedDatabaseWorker;


/**
 * Description:
 * Author: kanjianxiong
 * Date : 2021/3/16 16:21
 **/
@Database(version = 1,
        exportSchema = false,
        entities = Plant.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase roomDatabase;

    public static AppDatabase getInstance() {
        if (roomDatabase == null) {
            throw new IllegalArgumentException("AppDatabase not init...");
        }
        return roomDatabase;
    }

    public static void init(final Context context) {
        if (roomDatabase == null) {
            synchronized (AppDatabase.class) {
                if (roomDatabase == null) {
                    roomDatabase = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "room_db.db")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    //数据库创建
                                    WorkManager.getInstance(context).enqueue(OneTimeWorkRequest.from(SeedDatabaseWorker.class));
                                }
                            })
                            .build();
                }
            }
        }
    }

    public abstract PlantDao getPlantDao();
} 
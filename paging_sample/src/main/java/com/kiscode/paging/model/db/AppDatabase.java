package com.kiscode.paging.model.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kiscode.paging.model.pojo.Student;

/****
 * Description: room数据库对象
 * Author:  keno
 * CreateDate: 2021/4/8 20:26
 */

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {

                    instance = Room.databaseBuilder(context, AppDatabase.class, "database").build();
                }
            }
        }
        return instance;
    }

    public abstract StudentDao getStudentDao();
}

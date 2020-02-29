package com.kiscode.jetpack.room.db;

import com.kiscode.jetpack.room.db.conveter.DateConverters;
import com.kiscode.jetpack.room.db.dao.AuthorDao;
import com.kiscode.jetpack.room.db.dao.BookDao;
import com.kiscode.jetpack.room.pojo.Author;
import com.kiscode.jetpack.room.pojo.Book;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * Description: 数据库声明
 * Author: Kris Keno
 * Date : 2020/2/29 10:24 AM
 **/
@Database(version = 1,
        exportSchema =false,
        entities = {Author.class, Book.class})
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    public abstract AuthorDao authorDao();
}

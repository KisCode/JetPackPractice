package com.kiscode.jetpack.room.db;

import android.content.Context;

import com.kiscode.jetpack.room.db.conveter.DateConverters;
import com.kiscode.jetpack.room.db.dao.AuthorDao;
import com.kiscode.jetpack.room.db.dao.BookDao;
import com.kiscode.jetpack.room.db.dao.ClassRoomDao;
import com.kiscode.jetpack.room.db.dao.StudentDao;
import com.kiscode.jetpack.room.pojo.Author;
import com.kiscode.jetpack.room.pojo.Book;
import com.kiscode.jetpack.room.pojo.ClassRoom;
import com.kiscode.jetpack.room.pojo.Student;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Description: 数据库声明
 * Author: Kris Keno
 * Date : 2020/2/29 10:24 AM
 **/
@Database(version = 3,
        exportSchema = false,
        entities = {Author.class, Book.class, ClassRoom.class, Student.class})
@TypeConverters({DateConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract BookDao bookDao();

    public abstract AuthorDao authorDao();

    public abstract ClassRoomDao classRoomDao();

    public abstract StudentDao studentDao();

    private static AppDatabase roomDatabase;

    public static void init(Context context) {
        if (roomDatabase == null) {
            synchronized (AppDatabase.class) {
                if (roomDatabase == null) {
                    roomDatabase = Room.databaseBuilder(context,
                            AppDatabase.class, "room_sample_dev.db")
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            .build();
                }
            }
        }
    }

    public static AppDatabase getInstance() {
        return roomDatabase;
    }

    /**
     * 数据库版本 1->2 user表格新增了name列
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE ClassRoom ADD COLUMN count INTEGER");
        }
    };

    /**
     * 数据库版本 2->3 新增了列
     */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE ClassRoom ADD COLUMN floor INTEGER");
        }
    };


//    /**
//     * 默认值是FrameworkSQLiteOpenHelperFactory，设置数据库的factory。比如我们想改变数据库的存储路径可以通过这个函数来实现
//     */
//    public RoomDatabase.Builder<T> openHelperFactory(@Nullable SupportSQLiteOpenHelper.Factory factory);
//
//    /**
//     * 设置数据库升级(迁移)的逻辑
//     */
//    public RoomDatabase.Builder<T> addMigrations(@NonNull Migration... migrations);
//
//    /**
//     * 设置是否允许在主线程做查询操作
//     */
//    public RoomDatabase.Builder<T> allowMainThreadQueries();
//
//    /**
//     * 设置数据库的日志模式
//     */
//    public RoomDatabase.Builder<T> setJournalMode(@NonNull JournalMode journalMode);
//
//    /**
//     * 设置迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
//     */
//    public RoomDatabase.Builder<T> fallbackToDestructiveMigration();
//
//    /**
//     * 设置从某个版本开始迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
//     */
//    public RoomDatabase.Builder<T> fallbackToDestructiveMigrationFrom(int... startVersions);
//
//    /**
//     * 监听数据库，创建和打开的操作
//     */
//    public RoomDatabase.Builder<T> addCallback(@NonNull RoomDatabase.Callback callback);


}

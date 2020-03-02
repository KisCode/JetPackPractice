package com.kiscode.jetpack.room.db.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kiscode.jetpack.room.pojo.Book;
import com.kiscode.jetpack.room.pojo.ClassRoom;

import java.util.List;

import io.reactivex.Observable;

/****
 * ProjectName: JetPackPracticeSample
 * Package: com.kiscode.jetpack.room.db.dao
 * ClassName: ClassRoomDao
 * Description:
 * Author:  Administrator
 * CreateDate: 2020/2/29 22:46
 */
@Dao
public interface ClassRoomDao {

    //------------------  insert  ------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClassRoomList(List<ClassRoom> classRoomList);

    @Query("SELECT *FROM ClassRoom")
    Observable<List<ClassRoom>> queryAllClassRoomList();
}

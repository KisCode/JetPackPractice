package com.kiscode.paging.model.db;


import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kiscode.paging.model.pojo.Student;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 20:23
 */
@Dao
public interface StudentDao {

    @Insert
    void insertStudentList(Student... studentList);

    @Query("select *from tb_student ORDER BY id")
//    LiveData<List<Student>> getAllStudentList();
    DataSource.Factory<Integer,Student> getAllStudentList();

    @Query("DELETE FROM tb_student")
    void deleteAllStudent();
}

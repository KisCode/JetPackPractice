package com.kiscode.jetpack.room.pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/****
 * ProjectName: JetPackPracticeSample
 * Package: com.kiscode.jetpack.room.pojo
 * ClassName: Student
 * Description: 学生表
 * Author:  Administrator
 * CreateDate: 2020/2/29 22:44
 */
@Entity
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

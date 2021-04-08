package com.kiscode.paging.model.pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/4/8 20:22
 */

@Entity(tableName = "tb_student")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}

package com.kiscode.jetpack.room.pojo;


import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

/****
 * ProjectName: JetPackPracticeSample
 * Package: com.kiscode.jetpack.room.pojo
 * ClassName: ClassRoom
 * Description: 包含内嵌集合对象的数据实体
 * Author:  Administrator
 * CreateDate: 2020/2/29 22:42
 */
@Entity
public class ClassRoom {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int number;

    private String name;

    private Integer count;

    //楼层
    private Integer floor;

    public ClassRoom() {
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", floor=" + floor +
                '}';
    }
}

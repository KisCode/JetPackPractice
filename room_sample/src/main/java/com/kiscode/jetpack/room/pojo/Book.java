package com.kiscode.jetpack.room.pojo;


import java.util.Date;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

@Entity
public class Book {
    @PrimaryKey
    private int id;
    private String name;
    private float price;
    private Date pubulisTime;

    //嵌入对象,通过prefix添加前缀避免字段重复
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded(prefix = "author")
    private Author author;

    public Book() {
        //room require
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getPubulisTime() {
        return pubulisTime;
    }

    public void setPubulisTime(Date pubulisTime) {
        this.pubulisTime = pubulisTime;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pubulisTime=" + pubulisTime +
                ", author=" + author.toString() +
                '}';
    }
}

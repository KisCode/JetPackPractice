package com.kiscode.jetpack.practice.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomWarnings;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:20
 **/
@Entity(tableName = "tb_favorite_plant")
public class FavoritePlant {
    @NonNull
    @PrimaryKey
    private String id;

    //嵌入对象,通过prefix添加前缀避免字段重复
    @SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
    @Embedded
    private Plant plant;
    private long favoriteTime;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public long getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(long favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
}
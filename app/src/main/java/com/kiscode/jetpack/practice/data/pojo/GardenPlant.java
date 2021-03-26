package com.kiscode.jetpack.practice.data.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

/**
 * Description: 花园
 * Author: keno
 * Date : 2021/3/18 14:20
 **/
@Entity(tableName = "tb_garden_plant"
        , indices = {@Index(value = {"plant_id"}, unique = true)})
public final class GardenPlant {
    @NonNull
    @PrimaryKey(autoGenerate = true)        //自增长
    @ColumnInfo(name = "garden_plant_id")
    private long id;


    @ColumnInfo(name = "plant_id")
    private String plantId;

    private Calendar favoriteTime;

    public GardenPlant(String plantId, Calendar favoriteTime) {
        this.plantId = plantId;
        this.favoriteTime = favoriteTime == null ? Calendar.getInstance() : favoriteTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlantId() {
        return plantId;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public Calendar getFavoriteTime() {
        return favoriteTime;
    }

    public void setFavoriteTime(Calendar favoriteTime) {
        this.favoriteTime = favoriteTime;
    }
}
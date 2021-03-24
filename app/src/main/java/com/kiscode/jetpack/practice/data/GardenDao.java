package com.kiscode.jetpack.practice.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kiscode.jetpack.practice.data.pojo.GardenPlant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:44
 **/
@Dao
public interface GardenDao {

    @Query("SELECT * FROM tb_garden_plant ORDER BY favoriteTime desc")
    LiveData<List<GardenPlant>> queryAll();

    @Query("SELECT * FROM tb_garden_plant where plant_id = :plantId")
    LiveData<GardenPlant> queryById(String plantId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveOrUpdate(@NonNull GardenPlant item);
} 
package com.kiscode.jetpack.practice.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kiscode.jetpack.practice.data.pojo.Plant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 16:25
 **/
@Dao
public interface PlantDao {

    @Query("SELECT * FROM tb_plant ORDER BY name")
    LiveData<List<Plant>> getPlants();

    @Query("SELECT * FROM tb_plant where plantId = :plantId")
    LiveData<Plant> getPlantById(String plantId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Plant> list);
} 
package com.kiscode.jetpack.practice.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.kiscode.jetpack.practice.data.pojo.FavoritePlant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:44
 **/
@Dao
public interface GardenDao {

    @Query("SELECT * FROM tb_favorite_plant ORDER BY favoriteTime desc")
    LiveData<List<FavoritePlant>> queryAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveOrUpdate(FavoritePlant item);
} 
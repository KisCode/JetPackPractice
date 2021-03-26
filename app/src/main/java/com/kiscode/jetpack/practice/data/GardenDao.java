package com.kiscode.jetpack.practice.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.kiscode.jetpack.practice.data.pojo.GardenPlant;
import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;

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

    //用于返回将父实体与子实体配对的数据类的所有实例。该方法需要 Room 运行两次查询，因此应向该方法添加 @Transaction 注释，以确保整个操作以原子方式执行
    @Transaction
    @Query("SELECT *FROM tb_garden_plant")
    LiveData<List<GardenWithPlant>> queryAllGardenWithPlant();
} 
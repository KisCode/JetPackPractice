package com.kiscode.jetpack.practice.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.AppDatabase;
import com.kiscode.jetpack.practice.data.GardenDao;
import com.kiscode.jetpack.practice.data.PlantDao;
import com.kiscode.jetpack.practice.data.pojo.GardenPlant;
import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:49
 **/
public class GardenViewModel extends ViewModel {
    public final LiveData<List<GardenWithPlant>> gardenWithPlant;

    public GardenViewModel() {
        GardenDao gardenDao = AppDatabase.getInstance().getGardenDao();
        PlantDao plantDao = AppDatabase.getInstance().getPlantDao();
        LiveData<List<GardenPlant>> gardenPlants = gardenDao.queryAll();

        gardenWithPlant = gardenDao.queryAllGardenWithPlant();
    }

}
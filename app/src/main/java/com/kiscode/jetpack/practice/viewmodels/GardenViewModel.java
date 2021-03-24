package com.kiscode.jetpack.practice.viewmodels;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.AppDatabase;
import com.kiscode.jetpack.practice.data.GardenDao;
import com.kiscode.jetpack.practice.data.PlantDao;
import com.kiscode.jetpack.practice.data.pojo.GardenPlant;
import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:49
 **/
public class GardenViewModel extends ViewModel {
    public final LiveData<List<GardenWithPlant>> favoritePlantsLiveData;

    public GardenViewModel() {
        GardenDao gardenDao = AppDatabase.getInstance().getGardenDao();
        PlantDao plantDao = AppDatabase.getInstance().getPlantDao();
        LiveData<List<GardenPlant>> gardenPlants = gardenDao.queryAll();

        favoritePlantsLiveData = Transformations.map(gardenPlants, new Function<List<GardenPlant>, List<GardenWithPlant>>() {
            @Override
            public List<GardenWithPlant> apply(List<GardenPlant> gardenPlantList) {
                List<GardenWithPlant> removeItems = new ArrayList<>();

                return null;
            }
        });
    }

}
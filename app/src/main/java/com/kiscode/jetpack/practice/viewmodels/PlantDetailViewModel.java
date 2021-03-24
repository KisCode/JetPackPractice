package com.kiscode.jetpack.practice.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.AppDatabase;
import com.kiscode.jetpack.practice.data.GardenDao;
import com.kiscode.jetpack.practice.data.PlantDao;
import com.kiscode.jetpack.practice.data.pojo.GardenPlant;
import com.kiscode.jetpack.practice.data.pojo.Plant;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/24 13:36
 **/
public class PlantDetailViewModel extends ViewModel {
    private final PlantDao plantDao;
    private final GardenDao gardenDao;
    public LiveData<Plant> plant;
    public LiveData<Boolean> isPlanted;


    public PlantDetailViewModel() {
        plantDao = AppDatabase.getInstance().getPlantDao();
        gardenDao = AppDatabase.getInstance().getGardenDao();
    }

    public void loadById(String plantId) {
        //查询植物
        this.plant = plantDao.getPlantById(plantId);
        LiveData<GardenPlant> favoritePlantLiveData = gardenDao.queryById(plantId);
        this.isPlanted = Transformations.map(favoritePlantLiveData, input -> input != null);
    }

    public void addToGarden(String plantId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GardenPlant gardenPlant = new GardenPlant(plantId, null);
                gardenDao.saveOrUpdate(gardenPlant);
            }
        }).start();
    }
}
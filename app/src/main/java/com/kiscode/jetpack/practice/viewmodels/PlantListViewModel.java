package com.kiscode.jetpack.practice.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.AppDatabase;
import com.kiscode.jetpack.practice.data.PlantDao;
import com.kiscode.jetpack.practice.data.pojo.Plant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/16 15:28
 **/
public class PlantListViewModel extends ViewModel {
    private final LiveData<List<Plant>> plantsLiveData;

    public PlantListViewModel() {
        PlantDao plantDao = AppDatabase.getInstance().getPlantDao();
        plantsLiveData = plantDao.getPlants();
    }

    public LiveData<List<Plant>> getPlantsLiveData() {
        return plantsLiveData;
    }

}
package com.kiscode.jetpack.practice.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.AppDatabase;
import com.kiscode.jetpack.practice.data.GardenDao;
import com.kiscode.jetpack.practice.data.pojo.GardenPlant;

import java.util.List;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/18 14:49
 **/
public class GardenViewModel extends ViewModel {
    public final LiveData<List<GardenPlant>> favoritePlantsLiveData;

    public GardenViewModel() {
        GardenDao gardenDao = AppDatabase.getInstance().getGardenDao();
        this.favoritePlantsLiveData = gardenDao.queryAll();
    }

}
package com.kiscode.jetpack.practice.viewmodels;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.kiscode.jetpack.practice.data.pojo.GardenWithPlant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Description:
 * Author: keno
 * Date : 2021/3/26 16:26
 **/
public class GardenItemViewModel extends ViewModel {
    public final ObservableField<String> imageUrl;
    public final ObservableField<String> plantDateStr;
    public final ObservableField<String> plantName;
    public final ObservableField<String> waterInterval;

    public GardenItemViewModel(GardenWithPlant gardenWithPlant) {
        imageUrl = new ObservableField<>(gardenWithPlant.getPlant().getImageUrl());
        plantName = new ObservableField<>(gardenWithPlant.getPlant().getName());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:hh:ss");
        String dateStr = dateFormat.format(gardenWithPlant.getGardenPlant().getFavoriteTime().getTime());
        plantDateStr = new ObservableField<>(dateStr);

        waterInterval = new ObservableField<>(String.valueOf(gardenWithPlant.getPlant().getWateringInterval()));
    }
}
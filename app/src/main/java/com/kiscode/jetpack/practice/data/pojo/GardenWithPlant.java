package com.kiscode.jetpack.practice.data.pojo;


import androidx.room.Embedded;
import androidx.room.Relation;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/3/24 21:32
 */

public class GardenWithPlant {
    @Embedded
    private GardenPlant gardenPlant;

    @Relation(parentColumn = "plant_id", entityColumn = "plantId")
    private Plant plant;

    public GardenPlant getGardenPlant() {
        return gardenPlant;
    }

    public void setGardenPlant(GardenPlant gardenPlant) {
        this.gardenPlant = gardenPlant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}

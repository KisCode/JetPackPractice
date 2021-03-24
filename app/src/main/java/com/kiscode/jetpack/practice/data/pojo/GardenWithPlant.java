package com.kiscode.jetpack.practice.data.pojo;


import androidx.room.Embedded;
import androidx.room.Relation;

/****
 * Description: 
 * Author:  keno
 * CreateDate: 2021/3/24 21:32
 */

public class GardenWithPlant {
    @Relation(parentColumn = "id", entityColumn = "plant_id")
    private GardenPlant gardenPlant;

    @Embedded
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

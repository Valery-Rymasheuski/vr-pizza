package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.lifecycle.LiveData;

import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodTypeDao;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

public class FoodTypeRepository {

    private FoodTypeDao foodTypeDao;

    public FoodTypeRepository(FoodTypeDao foodTypeDao) {
        this.foodTypeDao = foodTypeDao;
    }

    public LiveData<List<FoodType>> getFoodTypes(){
        return foodTypeDao.getActiveFoodTypes();
    }




}

package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.lifecycle.LiveData;

import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodDao;

import java.util.Collections;
import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

public class FoodRepository {

    private FoodDao foodDao;


    public FoodRepository(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public LiveData<List<Food>> getFoodList(int foodTypeId){
        return foodDao.getActiveByTypeId(foodTypeId);


    }
}

package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rymasheuski.valery.vrpizza.model.database.dao.DataVersionDao;
import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodDao;
import com.example.rymasheuski.valery.vrpizza.model.web.FoodWebservice;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

public class FoodRepository {

    private FoodDao mFoodDao;

    private FoodWebservice mFoodWebservice;

    private DataVersionDao mDataVersionDao;


    public FoodRepository(FoodDao foodDao, FoodWebservice foodWebservice, DataVersionDao dataVersionDao) {
        this.mFoodDao = foodDao;
        this.mFoodWebservice = foodWebservice;
        this.mDataVersionDao = dataVersionDao;
    }

    public LiveData<List<Food>> getFoodList(int foodTypeId){
        AsyncTask.execute(() -> { //TODO call for each typeId !!!
            DataVersion dataVersion = mDataVersionDao.getVersion(DataVersion.Entity.FOOD.name());
            mFoodWebservice.getFood((list) -> {}, DataVersion.getSafeVersion(dataVersion));
        });

        return mFoodDao.getActiveByTypeId(foodTypeId);


    }
}

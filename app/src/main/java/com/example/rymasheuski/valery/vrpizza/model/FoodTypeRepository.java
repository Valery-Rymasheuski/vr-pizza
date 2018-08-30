package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.os.SystemClock;

import com.example.rymasheuski.valery.vrpizza.model.database.dao.DataVersionDao;
import com.example.rymasheuski.valery.vrpizza.model.database.dao.FoodTypeDao;
import com.example.rymasheuski.valery.vrpizza.model.web.FoodWebservice;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

public class FoodTypeRepository {

    private FoodTypeDao mFoodTypeDao;

    private FoodWebservice mFoodWebservice;

    private DataVersionDao mDataVersionDao;

    public FoodTypeRepository(FoodTypeDao mFoodTypeDao, FoodWebservice mFoodWebservice, DataVersionDao mDataVersionDao) {
        this.mFoodTypeDao = mFoodTypeDao;
        this.mFoodWebservice = mFoodWebservice;
        this.mDataVersionDao = mDataVersionDao;
    }

    public LiveData<List<FoodType>> getFoodTypes(){
        AsyncTask.execute(() -> {
            SystemClock.sleep(5000);
            DataVersion dataVersion = mDataVersionDao.getVersion(DataVersion.Entity.FOOD_TYPE.name());
            int version = DataVersion.getSafeVersion(dataVersion);
            mFoodWebservice.getFoodType( (list) -> mFoodTypeDao.insert(list), version);
        });


        return mFoodTypeDao.getActiveFoodTypes();
    }




}

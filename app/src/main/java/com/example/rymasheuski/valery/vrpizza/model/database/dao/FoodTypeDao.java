package com.example.rymasheuski.valery.vrpizza.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rymasheuski.valery.vrpizza.model.FoodType;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

@Dao
public interface FoodTypeDao {

    @Query("SELECT * FROM food_type WHERE active = 1 ")
    LiveData<List<FoodType>> getActiveFoodTypes();


    @Insert
    void insertTypes(List<FoodType> types);

}

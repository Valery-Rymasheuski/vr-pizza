package com.example.rymasheuski.valery.vrpizza.model.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.rymasheuski.valery.vrpizza.model.Food;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

@Dao
public interface FoodDao extends BaseDao<Food> {

    @Query("SELECT * FROM food WHERE type_id = :typeId AND active = 1")
    LiveData<List<Food>> getActiveByTypeId(int typeId);




}

package com.example.rymasheuski.valery.vrpizza.model.database.dao;

import android.arch.persistence.room.Insert;


import java.util.List;

/**
 * Created by valery on 30.8.18.
 */

public interface BaseDao<T> {

    @Insert
    void insert(List<T> list);
}

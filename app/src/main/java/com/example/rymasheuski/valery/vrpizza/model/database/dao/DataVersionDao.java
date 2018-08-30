package com.example.rymasheuski.valery.vrpizza.model.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.rymasheuski.valery.vrpizza.model.DataVersion;

/**
 * Created by valery on 30.8.18.
 */
@Dao
public interface DataVersionDao extends BaseDao<DataVersion> {

    @Query("SELECT * FROM data_version WHERE entity_name = :entityName ")
    DataVersion getVersion(String entityName);

}

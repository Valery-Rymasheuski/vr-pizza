package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by valery on 30.8.18.
 */
@Entity(tableName = "data_version")
public class DataVersion {

    private static final String TAG = DataVersion.class.getName();

    public static final int INITIAL_VERSION = 1;

    public enum Entity{FOOD_TYPE, FOOD}

    @PrimaryKey
    @ColumnInfo(name = "entity_name")
    @NonNull
    private String entityName;

    @ColumnInfo(name="version")
    private int version;


    public DataVersion(String entityName, int version) {
        this.entityName = entityName;
        this.version = version;
    }

    public DataVersion(Entity entity) {
        this.entityName = entity.name();
        this.version = INITIAL_VERSION;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    public static int getSafeVersion(DataVersion dataVersion){
        if(dataVersion == null){
            Log.e(TAG, "DataVersion is null");
            return INITIAL_VERSION;
        }else {
            return dataVersion.getVersion();
        }

    }
}

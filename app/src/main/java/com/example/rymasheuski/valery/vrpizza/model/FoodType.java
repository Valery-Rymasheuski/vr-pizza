package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by valery on 28.8.18.
 */
@Entity(tableName = "food_type")
public class FoodType {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "name_key")
    private String nameKey;

    @ColumnInfo(name = "active")
    private boolean active;

    public FoodType(int id, String nameKey, boolean active) {
        this.id = id;
        this.nameKey = nameKey;
        this.active = active;
    }

    public FoodType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

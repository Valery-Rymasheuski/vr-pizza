package com.example.rymasheuski.valery.vrpizza.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

/**
 * Created by valery on 2.8.18.
 */

@Entity(tableName = "food", foreignKeys = {@ForeignKey(entity = FoodType.class,
        parentColumns = "_id", childColumns = "type_id")})
public class Food extends Saleable {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    private Long id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name="desc")
    private String description;
    @ColumnInfo(name="price")
    private int price;
    @ColumnInfo(name="size")
    private int size;
    @ColumnInfo(name="type_id")
    private int typeId;
    @ColumnInfo(name="image_id")
    private Integer imageId;

    @ColumnInfo(name="active")
    private boolean active;

    @ColumnInfo(name="has_options")
    private boolean hasOptions;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Integer getImageId() {
        return imageId != null ? imageId : 0;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public double getPriceDecimal(){
        return FormatUtil.formatPrice(price);
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean isHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(boolean hasOptions) {
        this.hasOptions = hasOptions;
    }
}

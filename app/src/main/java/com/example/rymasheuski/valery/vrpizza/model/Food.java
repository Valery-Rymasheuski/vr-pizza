package com.example.rymasheuski.valery.vrpizza.model;

import com.example.rymasheuski.valery.vrpizza.util.Saleable;

/**
 * Created by valery on 2.8.18.
 */

public class Food extends Saleable {

    private Long id;

    private String name;
    private String description;
    private int price;
    private int size;
    private Integer imageId;


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
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }


    public boolean containsOptionSizes(){
        return false;
    }


    @Override
    public String getOptions() {
        return null;
    }
}

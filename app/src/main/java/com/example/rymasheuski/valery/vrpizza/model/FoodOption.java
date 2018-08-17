package com.example.rymasheuski.valery.vrpizza.model;

/**
 * Created by valery on 16.8.18.
 */

public class FoodOption {

    private int id;

    private String label;

    private int pricePercent;

    public FoodOption(int id, String label, int pricePercent) {
        this.id = id;
        this.label = label;
        this.pricePercent = pricePercent;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getPricePercent() {
        return pricePercent;
    }

    @Override
    public String toString() {
        return "FoodOption{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", pricePercent=" + pricePercent +
                '}';
    }
}

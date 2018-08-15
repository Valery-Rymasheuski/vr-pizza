package com.example.rymasheuski.valery.vrpizza.util;

import com.example.rymasheuski.valery.vrpizza.model.Food;

/**
 * Created by valery on 13.8.18.
 */

public abstract class Saleable {

    public abstract Long getId();

    public abstract String getName();

    public abstract String getOptions();

    public abstract String getDescription();

    public abstract int getPrice();

    public abstract int getSize();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Saleable product = (Saleable) o;

        return getId().equals(product.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }


}

package com.example.rymasheuski.valery.vrpizza.model;

import com.example.rymasheuski.valery.vrpizza.util.Saleable;

/**
 * Created by valery on 13.8.18.
 */

public class CartItem {

    private Saleable product;

    private int quantity;


    public CartItem(Saleable product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Saleable getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

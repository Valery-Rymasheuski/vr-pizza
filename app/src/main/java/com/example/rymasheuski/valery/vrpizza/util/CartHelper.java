package com.example.rymasheuski.valery.vrpizza.util;

import com.example.rymasheuski.valery.vrpizza.model.Cart;

/**
 * Created by valery on 13.8.18.
 */

public class CartHelper {

    private static Cart cart = new Cart();

    private CartHelper() {
    }


    public static Cart getCart(){
       return cart;
    }
}

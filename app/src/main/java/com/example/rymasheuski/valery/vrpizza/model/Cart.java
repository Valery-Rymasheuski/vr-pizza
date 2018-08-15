package com.example.rymasheuski.valery.vrpizza.model;

import android.util.Log;

import com.example.rymasheuski.valery.vrpizza.util.Saleable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by valery on 13.8.18.
 */

public class Cart {

    private static final String LOG_TAG = Cart.class.getName();

    private static final int ZERO_QUANTITY = 0;

    private Map<Saleable, Integer> itemMap = new HashMap<>();


    public Cart() {

    }


    public void addProduct(Saleable product, int quantity){
        if(quantity > 0) {
            itemMap.put(product, quantity);
        }
    }

    public int getQuantity(Saleable product){
        if(itemMap.containsKey(product)){
            return itemMap.get(product);
        }else{
            return ZERO_QUANTITY;
        }

    }


    public List<CartItem> getCartItems(){
        List<CartItem> items = new ArrayList<>();
        for(Map.Entry<Saleable, Integer> entry : itemMap.entrySet()){
            items.add(new CartItem(entry.getKey(), entry.getValue()));
        }
        return items;
    }

    public void clear(){
        itemMap.clear();
    }


}

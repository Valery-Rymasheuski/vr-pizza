package com.example.rymasheuski.valery.vrpizza.model;

import android.util.Log;
import android.util.LongSparseArray;

import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
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

    private LongSparseArray<CartItem> productIdToCartItemArray = new LongSparseArray<>(5);


    public Cart() {

    }


    public void addProduct(Saleable product, int quantity){
        CartItem item = productIdToCartItemArray.get(product.getId());
        if(item != null){
            item.setQuantity(quantity);
        }else{
            productIdToCartItemArray.put(product.getId(), new CartItem(product, quantity));
        }


    }

    public int getQuantity(Saleable product){
        CartItem item = productIdToCartItemArray.get(product.getId());
        if(item != null){
            return item.getQuantity();
        }else{
            return ZERO_QUANTITY;
        }

    }


    public List<CartItem> getCartItems(){
        List<CartItem> items = new ArrayList<>();
        for(int i = 0; i < productIdToCartItemArray.size(); i++){
            items.add(productIdToCartItemArray.valueAt(i));
        }

        return items;
    }

    public void clear(){
        productIdToCartItemArray.clear();
        FoodOptionsHelper.clearAll();
    }


}

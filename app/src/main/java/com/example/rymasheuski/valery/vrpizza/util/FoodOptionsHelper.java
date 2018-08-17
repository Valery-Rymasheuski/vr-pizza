package com.example.rymasheuski.valery.vrpizza.util;

import android.util.LongSparseArray;

import com.example.rymasheuski.valery.vrpizza.model.FoodOption;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by valery on 16.8.18.
 */

public class FoodOptionsHelper {

    private LongSparseArray<FoodOption>  productIdToOptions = new LongSparseArray<>(5);

    public static FoodOptionsHelper sizeOptionHelper = new FoodOptionsHelper();

    public static FoodOptionsHelper pizzaOptionHelper = new FoodOptionsHelper();



    private FoodOptionsHelper() {
    }




    public void putOption(Long productId, FoodOption option){

        productIdToOptions.put(productId, option);
    }


    public FoodOption getOption(Long productId){
        return productIdToOptions.get(productId);
    }


    public void clear(){
        productIdToOptions.clear();
    }


    public static void clearAll(){
        sizeOptionHelper.clear();
        pizzaOptionHelper.clear();
    }


    public static List<FoodOption> getSelectedOptions(Long productId){
        FoodOption sizeOption = sizeOptionHelper.getOption(productId);
        FoodOption pizzaOption = pizzaOptionHelper.getOption(productId);

        if(sizeOption == null && pizzaOption == null){
            return Collections.emptyList();
        }

        List<FoodOption> list = new ArrayList<>(2);
        if(sizeOption != null){
            list.add(sizeOption);
        }
        if(pizzaOption != null){
            list.add(pizzaOption);
        }
        return list;
    }

    public static int getPriceWithOptions(Long productId, int productPrice){

        List<FoodOption> selectedOptions = getSelectedOptions(productId);

        int price = productPrice;

        for(FoodOption option : selectedOptions){
            price += price * option.getPricePercent() / 100;
        }

        return price;
    }

    public static int getWeightWithOptions(Long productId, int weight){

       return getPriceWithOptions(productId, weight); //TODO write own
    }



}

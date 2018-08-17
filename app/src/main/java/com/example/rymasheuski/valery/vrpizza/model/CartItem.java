package com.example.rymasheuski.valery.vrpizza.model;

import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by valery on 13.8.18.
 */

public class CartItem {

    private Saleable product;

    private List<FoodOption> selectedOptions;

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


    public List<FoodOption> getSelectedOptions() {
        if(selectedOptions == null){
            return Collections.emptyList();
        }
        return selectedOptions;
    }

    public void clearSelectedOptions(){
        if(selectedOptions != null){
            selectedOptions.clear();
        }
    }

    public void addSelectedOption(FoodOption selectedOption) {
        if(selectedOption == null){
            return;
        }

        if(selectedOptions == null){
            selectedOptions = new ArrayList<>();
        }
        selectedOptions.add(selectedOption);
    }


    public int getPriceWithOptions(){

        return FoodOptionsHelper.getPriceWithOptions(getProduct().getId(), getProduct().getPrice());

    }

    public int getWeightWithOptions(){

        return FoodOptionsHelper.getWeightWithOptions(getProduct().getId(), getProduct().getSize());

    }


    public String getOptionsLabel(){
        if(selectedOptions == null){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for(FoodOption option : selectedOptions){
            if(!first){
                sb.append(", ");
            }else{
                first = false;
            }

            sb.append(option.getLabel());
        }
        return sb.toString();
    }
}

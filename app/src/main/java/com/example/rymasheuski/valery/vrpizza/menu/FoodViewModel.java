package com.example.rymasheuski.valery.vrpizza.menu;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import android.databinding.ObservableDouble;

import android.databinding.ObservableInt;

import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;

/**
 * Created by valery on 28.8.18.
 */

public class FoodViewModel extends ViewModel {

    public final Food food;

    public final MutableLiveData<Integer> quantity = new MutableLiveData<>();
    public final ObservableDouble priceWithOptions = new ObservableDouble();
    public final ObservableInt weightWithOptions = new ObservableInt();


    public final MutableLiveData<FoodOption> sizeOption = new MutableLiveData<>();
    public final MutableLiveData<FoodOption> pizzaOption = new MutableLiveData<>();


    public FoodViewModel(Food food) {
        this.food = food;

        init();


        sizeOption.observeForever((option) -> {
            changePriceAndWeight();
            saveOption(FoodOptionsHelper.sizeOptionHelper, food.getId(), sizeOption);

        });

        pizzaOption.observeForever((option) -> {
            changePriceAndWeight();
            saveOption(FoodOptionsHelper.pizzaOptionHelper, food.getId(), pizzaOption);

        });

        quantity.observeForever((value) -> {
            if(value != null && value > 0) {
                CartHelper.getCart().addProduct(food, value);
            }else{
                CartHelper.getCart().removeProduct(food);
            }

        });

        changePriceAndWeight();
    }


    public boolean isContainsOptions(){
        return food.containsOptionSizes();
    }


    private void init(){
        int intQuantity = CartHelper.getCart().getQuantity(food);
        quantity.setValue(intQuantity);
        Long foodId = food.getId();
        setOption(FoodOptionsHelper.pizzaOptionHelper, foodId, pizzaOption);
        setOption(FoodOptionsHelper.sizeOptionHelper, foodId, sizeOption);

    }

    private void setOption(FoodOptionsHelper helper, Long foodId, MutableLiveData<FoodOption> optionField){
        optionField.setValue(helper.getOption(foodId));

    }


    private void saveOption(FoodOptionsHelper helper, Long foodId, MutableLiveData<FoodOption> optionField){
        FoodOption option = optionField.getValue();
        if(option != null) {
            helper.putOption(foodId, option);
        }
    }


    private void changePriceAndWeight(){
        FoodOption sizeOptionValue = sizeOption.getValue();
        FoodOption pizzaOptionValue = pizzaOption.getValue();
        int price = FoodOptionsHelper.getPriceWithOptions(sizeOptionValue, pizzaOptionValue, food.getPrice());
        priceWithOptions.set(FormatUtil.formatPrice(price));

        int weight = FoodOptionsHelper.getWeightWithOptions(sizeOptionValue, pizzaOptionValue, food.getSize());
        weightWithOptions.set(weight);
    }



}

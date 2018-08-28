package com.example.rymasheuski.valery.vrpizza.menu;

import android.databinding.Observable;
import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;

/**
 * Created by valery on 28.8.18.
 */

public class FoodViewModel {

    public final Food food;

    public final ObservableInt quantity = new ObservableInt();
    public final ObservableDouble priceWithOptions = new ObservableDouble();
    public final ObservableInt weightWithOptions = new ObservableInt();


    public final ObservableField<FoodOption> sizeOption = new ObservableField<>();
    public final ObservableField<FoodOption> pizzaOption = new ObservableField<>();


    public FoodViewModel(Food food) {
        this.food = food;

        init();


        sizeOption.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                changePriceAndWeight();
                saveOption(FoodOptionsHelper.sizeOptionHelper, food.getId(), sizeOption);
            }
        });

        pizzaOption.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                changePriceAndWeight();
                saveOption(FoodOptionsHelper.pizzaOptionHelper, food.getId(), pizzaOption);
            }
        });

        quantity.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                int value = quantity.get();
                if(value > 0) {
                    CartHelper.getCart().addProduct(food, quantity.get());
                }else{
                    CartHelper.getCart().removeProduct(food);
                }
            }
        });

        changePriceAndWeight();
    }


    public boolean isContainsOptions(){
        return food.containsOptionSizes();
    }


    private void init(){
        quantity.set(CartHelper.getCart().getQuantity(food));
        Long foodId = food.getId();
        setOption(FoodOptionsHelper.pizzaOptionHelper, foodId, pizzaOption);
        setOption(FoodOptionsHelper.sizeOptionHelper, foodId, sizeOption);

    }

    private void setOption(FoodOptionsHelper helper, Long foodId, ObservableField<FoodOption> optionField){
        optionField.set(helper.getOption(foodId));

    }


    private void saveOption(FoodOptionsHelper helper, Long foodId, ObservableField<FoodOption> optionField){
        FoodOption option = optionField.get();
        if(option != null) {
            helper.putOption(foodId, option);
        }
    }


    private void changePriceAndWeight(){
        int price = FoodOptionsHelper.getPriceWithOptions(sizeOption.get(), pizzaOption.get(), food.getPrice());
        priceWithOptions.set(FormatUtil.formatPrice(price));

        int weight = FoodOptionsHelper.getWeightWithOptions(sizeOption.get(), pizzaOption.get(), food.getSize());
        weightWithOptions.set(weight);
    }



}

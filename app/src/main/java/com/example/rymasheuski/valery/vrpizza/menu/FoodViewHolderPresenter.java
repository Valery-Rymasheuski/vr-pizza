package com.example.rymasheuski.valery.vrpizza.menu;


import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseMvpViewHolderPresenter;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;

/**
 * Created by valery on 20.8.18.
 */

public class FoodViewHolderPresenter extends BaseMvpViewHolderPresenter<Food, FoodViewHolderContract.MvpView> implements FoodViewHolderContract.MvpPresenter {



    @Override
    public void onViewIsReady() {
        Food food = getModel();

        getMvpView().setFoodInfo(food);

        if(food.containsOptionSizes()){
            Long foodId = food.getId();
            FoodOption sizeOption = FoodOptionsHelper.sizeOptionHelper.getOption(foodId);
            FoodOption pizzaOption = FoodOptionsHelper.pizzaOptionHelper.getOption(foodId);

            getMvpView().setOptions(foodId, sizeOption, pizzaOption);
        }

        showPriceAndWeight(food);

        getMvpView().setQuantity(CartHelper.getCart().getQuantity(food));


    }

    @Override
    public void onSizeOptionChanged(Long foodId, FoodOption selectedOption) {
        FoodOptionsHelper.sizeOptionHelper.putOption(foodId, selectedOption);
        showPriceAndWeight(getModel());
    }

    @Override
    public void onPizzaOptionChanged(Long foodId, FoodOption selectedOption) {
        FoodOptionsHelper.pizzaOptionHelper.putOption(foodId, selectedOption);
        showPriceAndWeight(getModel());
    }

    @Override
    public void onQuantityChanged(int quantity) {
        CartHelper.getCart().addProduct(getModel(), quantity);
    }

    private void showPriceAndWeight(Food food){
        int price = FoodOptionsHelper.getPriceWithOptions(food.getId(), food.getPrice());
        int weight = FoodOptionsHelper.getWeightWithOptions(food.getId(), food.getSize());
        getMvpView().setPriceAndWeight(price, weight);


    }
}

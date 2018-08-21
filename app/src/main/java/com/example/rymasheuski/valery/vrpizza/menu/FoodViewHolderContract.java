package com.example.rymasheuski.valery.vrpizza.menu;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;


/**
 * Created by valery on 20.8.18.
 */

public interface FoodViewHolderContract {



    interface MvpView extends BaseMvpContract.MvpView{

        void setFoodInfo(Food food);


        void setOptions(Long foodId, FoodOption sizeOption, FoodOption pizzaOption);

        void setPriceAndWeight(int price, int weight);

        void setQuantity(int quantity);


    }


    interface MvpPresenter extends BaseMvpContract.MvpPresenter<FoodViewHolderContract.MvpView>{


        void onSizeOptionChanged(Long foodId, FoodOption selectedOption);

        void onPizzaOptionChanged(Long foodId, FoodOption selectedOption);

        void onQuantityChanged(int quantity);

    }
}

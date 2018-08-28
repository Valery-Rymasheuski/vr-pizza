package com.example.rymasheuski.valery.vrpizza.menu;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.dummy.DummyDatabaseContent;
import com.example.rymasheuski.valery.vrpizza.model.Food;


import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public class FoodListViewModel extends AndroidViewModel {

    public ObservableList<FoodViewModel> foods = new ObservableArrayList<>();

    private boolean mInitialized = false;


    public FoodListViewModel(@NonNull Application application) {
        super(application);
    }


    public void init(int tabIndex){
        if(!mInitialized) {
            addFoods(tabIndex);

            mInitialized = true;
        }
    }

    private void addFoods(int tabIndex){
        List<Food> foodList = DummyDatabaseContent.getAllFoods().get(tabIndex);
        for(Food f: foodList){
            foods.add(new FoodViewModel(f));
        }
    }
}

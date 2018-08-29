package com.example.rymasheuski.valery.vrpizza.menu;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodRepository;


import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public class FoodListViewModel extends AndroidViewModel {

    private FoodRepository mFoodRepository;

    public ObservableList<FoodViewModel> foods = new ObservableArrayList<>(); //TODO remove

    private LiveData<List<Food>> mListLiveData;

    private boolean mInitialized = false;


    public FoodListViewModel(@NonNull Application application, FoodRepository mFoodRepository) {
        super(application);
        this.mFoodRepository = mFoodRepository;
    }

    public void init(int tabIndex){
        if(!mInitialized) {
            addFoods(tabIndex);

            mInitialized = true;
        }
    }

    private void addFoods(int foodTypeId){
        mListLiveData = mFoodRepository.getFoodList(foodTypeId);
        mListLiveData.observeForever(foodList -> { //TODO refactor
            if(foodList != null) {
                for (Food f : foodList) {
                    foods.add(new FoodViewModel(f));
                }
            }
        });


    }
}

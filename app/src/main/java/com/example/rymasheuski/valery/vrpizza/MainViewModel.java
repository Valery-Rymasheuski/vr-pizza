package com.example.rymasheuski.valery.vrpizza;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.model.FoodType;
import com.example.rymasheuski.valery.vrpizza.model.FoodTypeRepository;

import java.util.List;

/**
 * Created by valery on 28.8.18.
 */

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<FoodType>> mFoodTypes;

    private FoodTypeRepository mFoodTypeRepository;

    private boolean mInitialized;


    public MainViewModel(@NonNull Application application, @NonNull FoodTypeRepository foodTypeRepository) {
        super(application);
        mFoodTypeRepository = foodTypeRepository;
    }



    public void init(){
        if(!mInitialized){
            mFoodTypes = mFoodTypeRepository.getFoodTypes();
            mInitialized = true;
        }
    }


    public LiveData<List<FoodType>> getFoodTypes() {
        return mFoodTypes;
    }
}

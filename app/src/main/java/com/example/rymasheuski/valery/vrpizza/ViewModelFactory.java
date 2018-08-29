package com.example.rymasheuski.valery.vrpizza;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.cart.ShoppingCartViewModel;
import com.example.rymasheuski.valery.vrpizza.menu.FoodListViewModel;
import com.example.rymasheuski.valery.vrpizza.model.FoodRepository;
import com.example.rymasheuski.valery.vrpizza.model.FoodTypeRepository;
import com.example.rymasheuski.valery.vrpizza.model.database.PizzaDatabase;
import com.example.rymasheuski.valery.vrpizza.placeorder.PlaceOrderViewModel;

/**
 * Created by valery on 22.8.18.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;
    private final FoodTypeRepository mFoodTypeRepository;
    private final FoodRepository mFoodRepository;


    public static ViewModelFactory getInstance(Application application){
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private ViewModelFactory(Application mApplication) {
        this.mApplication = mApplication;


        PizzaDatabase db = PizzaDatabase.getInstance(mApplication);

        mFoodTypeRepository = new FoodTypeRepository(db.getFoodTypeDao());
        mFoodRepository = new FoodRepository(db.getFoodDao());
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(PlaceOrderViewModel.class)){
            return (T) new PlaceOrderViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(ShoppingCartViewModel.class)){
            return (T) new ShoppingCartViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(FoodListViewModel.class)){
            return (T) new FoodListViewModel(mApplication, mFoodRepository);
        }else if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(mApplication, mFoodTypeRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

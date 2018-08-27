package com.example.rymasheuski.valery.vrpizza;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.rymasheuski.valery.vrpizza.cart.ShoppingCartViewModel;
import com.example.rymasheuski.valery.vrpizza.placeorder.PlaceOrderViewModel;

/**
 * Created by valery on 22.8.18.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;


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
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(PlaceOrderViewModel.class)){
            return (T) new PlaceOrderViewModel(mApplication);
        }else if(modelClass.isAssignableFrom(ShoppingCartViewModel.class)){
            return (T) new ShoppingCartViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}

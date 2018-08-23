package com.example.rymasheuski.valery.vrpizza.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;

import com.example.rymasheuski.valery.vrpizza.ViewModelFactory;


/**
 * Created by valery on 22.8.18.
 */

public class InjectionUtil {


    public static <T extends ViewModel> T  getViewModel(FragmentActivity activity, Class<T> viewModelClass){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(viewModelClass);
    }
}

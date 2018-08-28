package com.example.rymasheuski.valery.vrpizza.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.rymasheuski.valery.vrpizza.ViewModelFactory;


/**
 * Created by valery on 22.8.18.
 */

public class InjectionUtil {

    private static final String DEFAULT_KEY = "com.example.rymasheuski.valery.vrpizza.util.InjectionUtil.DefaultKey";


    public static <T extends ViewModel> T  getViewModel(FragmentActivity activity, Class<T> viewModelClass){
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        return ViewModelProviders.of(activity, factory).get(viewModelClass);
    }


    public static <T extends ViewModel> T  getViewModel(Fragment fragment, int fragmentIndex, Class<T> viewModelClass){
        ViewModelFactory factory = ViewModelFactory.getInstance(fragment.getActivity().getApplication());

        String key = getKey(viewModelClass, fragmentIndex);

        return ViewModelProviders.of(fragment, factory).get(key, viewModelClass);
    }


    private static <T> String getKey(Class<T> modelClass, int index){
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return new StringBuilder(DEFAULT_KEY)
                .append(":")
                .append(canonicalName)
                .append(":")
                .append(index)
                .toString();
    }

}

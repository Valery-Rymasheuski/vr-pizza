package com.example.rymasheuski.valery.vrpizza.model.web;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.example.rymasheuski.valery.vrpizza.model.FoodType;

import java.util.Arrays;
import java.util.List;


/**
 * Created by valery on 29.8.18.
 */

public class FoodWebservice {

    private static final String TAG = FoodWebservice.class.getName();


    public void getFoodType(Callback<List<FoodType>> callback, int dataVersion){
        Log.i(TAG, "[getFoodType]DataVersion=" + dataVersion);
       callback.onComplete(Arrays.asList(new FoodType(2, "chicken_tab", false)));
    }


    public void getFood(Callback<List<FoodType>> callback, int dataVersion){
        Log.i(TAG, "[getFood]DataVersion=" + dataVersion);
    }



    public interface Callback<T>{

        void onComplete(T data);

    }

}

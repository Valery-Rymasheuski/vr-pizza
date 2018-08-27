package com.example.rymasheuski.valery.vrpizza.util;

import android.content.Context;
import android.util.Log;

import com.example.rymasheuski.valery.vrpizza.R;

/**
 * Created by valery on 2.8.18.
 */

public class FormatUtil {
    public static final String LOG_TAG = FormatUtil.class.getName();


    public static String formatPrice(int price, Context context){
        double displayPrice = price / 100.0;
        Log.d(LOG_TAG, "Display price " + displayPrice);
        return context.getString(R.string.format_ruble, displayPrice);
    }

    public static double formatPrice(int price){
        return price / 100.0;

    }


    public static String formatSize(int size, Context context){
        return context.getString(R.string.format_size, size);
    }
}

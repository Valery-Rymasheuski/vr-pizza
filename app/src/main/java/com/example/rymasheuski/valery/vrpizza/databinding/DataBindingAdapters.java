package com.example.rymasheuski.valery.vrpizza.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by valery on 27.8.18.
 */

public class DataBindingAdapters {

    @BindingAdapter("custom:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}

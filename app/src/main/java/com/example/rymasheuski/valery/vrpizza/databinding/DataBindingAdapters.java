package com.example.rymasheuski.valery.vrpizza.databinding;

import android.databinding.BindingAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.rymasheuski.valery.vrpizza.MainActivity;
import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseRecyclerAdapter;
import com.example.rymasheuski.valery.vrpizza.model.FoodType;

import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public class DataBindingAdapters {

    @BindingAdapter("custom:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter("custom:items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.replaceData(items);
        }
    }

}

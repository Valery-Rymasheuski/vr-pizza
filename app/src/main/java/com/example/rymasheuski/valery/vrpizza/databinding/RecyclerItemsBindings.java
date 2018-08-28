package com.example.rymasheuski.valery.vrpizza.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseRecyclerAdapter;

import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public class RecyclerItemsBindings {

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

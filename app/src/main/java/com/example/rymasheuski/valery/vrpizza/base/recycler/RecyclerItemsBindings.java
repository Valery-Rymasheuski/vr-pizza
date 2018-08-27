package com.example.rymasheuski.valery.vrpizza.base.recycler;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public class RecyclerItemsBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerAdapter adapter = (BaseRecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null)
        {
            adapter.replaceData(items);
        }
    }
}

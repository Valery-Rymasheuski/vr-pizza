package com.example.rymasheuski.valery.vrpizza.base.recycler;

import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

/**
 * Created by valery on 27.8.18.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
        private List<T> mDataList;

        public void replaceData(List<T> dataList){
            mDataList = dataList;
            notifyDataSetChanged();
        }


    public List<T> getDataList() {
        return mDataList != null ? mDataList : Collections.emptyList();
    }

    @Override
    public int getItemCount() {
        return getDataList().size();
    }


    protected T getItem(int position){
        return getDataList().get(position);
    }
}

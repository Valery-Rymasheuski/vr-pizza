package com.example.rymasheuski.valery.vrpizza.base.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by valery on 21.8.18.
 */

public abstract class MvpRecyclerAdapter <M, P extends BaseMvpPresenter, VH extends MvpViewHolder> extends RecyclerView.Adapter<VH> {

    private final Map<Object, P> presenters;

    private final List<M> models;



    public MvpRecyclerAdapter() {
        presenters = new HashMap<>();
        models = new ArrayList<>();
    }


    public void clearAndAddAll(Collection<M> data) {
        models.clear();
        presenters.clear();

        for (M item : data) {
            addInternal(item);
        }

        notifyDataSetChanged();
    }

    private void addInternal(M item) {
        models.add(item);
        presenters.put(getModelId(item), createPresenter(item));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    protected M getItem(int position) {
        return models.get(position);
    }


    @NonNull
    protected P getPresenter(@NonNull M model) {
        return presenters.get(getModelId(model));
    }

    @NonNull protected abstract P createPresenter(@NonNull M model);

    @NonNull protected abstract Object getModelId(@NonNull M model);


    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);

        holder.unbindPresenter();
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }



}

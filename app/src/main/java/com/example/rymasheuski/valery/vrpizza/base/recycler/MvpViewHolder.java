package com.example.rymasheuski.valery.vrpizza.base.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;

/**
 * Created by valery on 21.8.18.
 */

public abstract class MvpViewHolder<P extends BaseMvpContract.MvpPresenter> extends RecyclerView.ViewHolder implements BaseMvpContract.MvpView{

    protected P mPresenter;


    public MvpViewHolder(View itemView) {
        super(itemView);
    }

    public void bindPresenter(P presenter) {
        this.mPresenter = presenter;
        mPresenter.attachView(this);
        mPresenter.onViewIsReady();
    }

    public void unbindPresenter() {
        mPresenter = null;
    }
}

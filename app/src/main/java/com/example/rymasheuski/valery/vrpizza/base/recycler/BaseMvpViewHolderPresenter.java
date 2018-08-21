package com.example.rymasheuski.valery.vrpizza.base.recycler;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;

/**
 * Created by valery on 21.8.18.
 */

public abstract class BaseMvpViewHolderPresenter<M, T extends BaseMvpContract.MvpView> extends BaseMvpPresenter<T> {

    private M mModel;


    public M getModel() {
        return mModel;
    }

    public void setModel(M model) {
        this.mModel = model;
    }
}

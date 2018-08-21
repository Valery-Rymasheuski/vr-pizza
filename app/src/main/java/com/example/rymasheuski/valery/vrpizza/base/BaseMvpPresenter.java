package com.example.rymasheuski.valery.vrpizza.base;

import android.util.Log;

/**
 * Created by valery on 20.8.18.
 */

public abstract class BaseMvpPresenter<T extends BaseMvpContract.MvpView> implements BaseMvpContract.MvpPresenter<T> {




    private T mMvpView;

    public BaseMvpPresenter(T mvpView) {
        attachView(mvpView);
    }

    public BaseMvpPresenter() {
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
        Log.i(this.getClass().getName(), "onAttachView ");
    }



    @Override
    public void detachView() {
        mMvpView = null;
        Log.i(this.getClass().getName(), "onDetachView ");
    }

    @Override
    public void destroy() {
        Log.i(this.getClass().getName(), "onDestroy ");
    }

    public T getMvpView(){
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }
}

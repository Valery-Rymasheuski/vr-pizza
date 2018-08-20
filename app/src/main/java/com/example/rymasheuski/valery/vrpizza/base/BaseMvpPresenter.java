package com.example.rymasheuski.valery.vrpizza.base;

/**
 * Created by valery on 20.8.18.
 */

public abstract class BaseMvpPresenter<T extends BaseMvpContract.MvpView> implements BaseMvpContract.MvpPresenter<T> {


    private T mMvpView;

    public BaseMvpPresenter(T mvpView) {
        attachView(mvpView);
    }

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }



    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void destroy() {

    }

    public T getMvpView(){
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }
}

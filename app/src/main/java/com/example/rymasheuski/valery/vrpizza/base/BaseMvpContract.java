package com.example.rymasheuski.valery.vrpizza.base;

/**
 * Created by valery on 20.8.18.
 */

public interface BaseMvpContract {

    interface MvpView{

    }



    interface MvpPresenter<V extends MvpView>{

        void attachView(V mvpView);

        void onViewIsReady();

        void detachView();

        void destroy();
    }
}

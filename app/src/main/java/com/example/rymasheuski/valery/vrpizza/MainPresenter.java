package com.example.rymasheuski.valery.vrpizza;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;

/**
 * Created by valery on 21.8.18.
 */

public class MainPresenter extends BaseMvpPresenter<MainMvpContract.MvpView> implements MainMvpContract.MvpPresenter {

    public MainPresenter(MainMvpContract.MvpView mvpView) {
        super(mvpView);
    }

    @Override
    public void onViewIsReady() {
        getMvpView().setTabs(getMvpView().getTabsFromResources());
    }
}

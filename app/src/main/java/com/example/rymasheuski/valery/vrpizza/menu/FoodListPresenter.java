package com.example.rymasheuski.valery.vrpizza.menu;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;
import com.example.rymasheuski.valery.vrpizza.dummy.DummyDatabaseContent;
import com.example.rymasheuski.valery.vrpizza.model.Food;

import java.util.List;

/**
 * Created by valery on 20.8.18.
 */

public class FoodListPresenter extends BaseMvpPresenter<FoodListContract.MvpView> implements FoodListContract.MvpPresenter {


    public FoodListPresenter(FoodListContract.MvpView mvpView) {
        super(mvpView);
    }

    @Override
    public void onViewIsReady() {
        int tabIndex = getMvpView().getTabIndex();
        List<Food> foods =  DummyDatabaseContent.getAllFoods().get(tabIndex);//TODO remove to repository

        getMvpView().showList(foods);
    }
}

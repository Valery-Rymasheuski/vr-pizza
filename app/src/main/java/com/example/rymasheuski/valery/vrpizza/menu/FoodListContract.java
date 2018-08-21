package com.example.rymasheuski.valery.vrpizza.menu;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.model.Food;


import java.util.List;

/**
 * Created by valery on 20.8.18.
 */

public interface FoodListContract {

    interface MvpView extends BaseMvpContract.MvpView{

        int getTabIndex();


        void showList(List<Food> list);



    }


    interface MvpPresenter extends BaseMvpContract.MvpPresenter<FoodListContract.MvpView>{



    }
}

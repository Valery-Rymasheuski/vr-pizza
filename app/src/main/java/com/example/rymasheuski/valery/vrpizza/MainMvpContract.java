package com.example.rymasheuski.valery.vrpizza;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;

/**
 * Created by valery on 21.8.18.
 */

public interface MainMvpContract {

    interface MvpView extends BaseMvpContract.MvpView{

        void setTabs(String tabs[]);

        String[] getTabsFromResources();

    }

    interface MvpPresenter extends BaseMvpContract.MvpPresenter<MvpView>{

    }
}

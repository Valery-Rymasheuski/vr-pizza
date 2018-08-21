package com.example.rymasheuski.valery.vrpizza.base;

import android.support.v4.app.Fragment;

/**
 * Created by valery on 21.8.18.
 */

public class BaseMvpFragment<P extends BaseMvpContract.MvpPresenter> extends Fragment {

    protected P mPresenter;



    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter.destroy();

    }
}

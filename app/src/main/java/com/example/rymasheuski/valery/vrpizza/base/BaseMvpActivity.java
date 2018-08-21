package com.example.rymasheuski.valery.vrpizza.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by valery on 21.8.18.
 */

public abstract class BaseMvpActivity<P extends BaseMvpContract.MvpPresenter>  extends AppCompatActivity {

    protected P mPresenter;



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        if(isFinishing()){
            mPresenter.destroy();
        }
    }

}

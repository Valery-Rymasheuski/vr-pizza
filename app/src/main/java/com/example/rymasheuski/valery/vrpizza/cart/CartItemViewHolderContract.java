package com.example.rymasheuski.valery.vrpizza.cart;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

/**
 * Created by valery on 21.8.18.
 */

public interface CartItemViewHolderContract {


    interface MvpView extends BaseMvpContract.MvpView{

        void setProductInfo(String name, String desc, String options);

        void setWeightAndPrice(int weight, int price);

        void setQuantity(int quantity);

        void callQuantityListener(Saleable product, int quantity);


    }

    interface MvpPresenter extends BaseMvpContract.MvpPresenter<CartItemViewHolderContract.MvpView>{

        void onQuantityChanged(int quantity);


    }



}

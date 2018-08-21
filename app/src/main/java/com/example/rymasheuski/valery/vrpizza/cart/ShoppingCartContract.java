package com.example.rymasheuski.valery.vrpizza.cart;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;

import java.util.List;

/**
 * Created by valery on 21.8.18.
 */

public interface ShoppingCartContract {

    interface MvpView extends BaseMvpContract.MvpView {

        void goToPlaceOrder();

        void setTotal(int total);

        void setCartItems(List<CartItem> cartItems);

    }


    interface MvpPresenter extends BaseMvpContract.MvpPresenter<ShoppingCartContract.MvpView>{

        void onClearOrderClicked();

        void onOrderClicked();

        void onChangeQuantity();

    }

}

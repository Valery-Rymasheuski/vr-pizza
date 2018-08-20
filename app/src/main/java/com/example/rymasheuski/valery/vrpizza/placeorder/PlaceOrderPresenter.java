package com.example.rymasheuski.valery.vrpizza.placeorder;


import android.util.Log;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpPresenter;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.Validator;

/**
 * Created by valery on 20.8.18.
 */

public class PlaceOrderPresenter extends BaseMvpPresenter<PlaceOrderContract.MvpView> implements PlaceOrderContract.MvpPresenter {

    private static final String TAG = PlaceOrderPresenter.class.getName();


    PlaceOrderPresenter(PlaceOrderContract.MvpView mvpView) {
        super(mvpView);
    }

    @Override
    public void onViewIsReady() {

    }

    @Override
    public void onSend() {

        Order order = getMvpView().getFormData();
        if(validateAndShowErrors(order)){
            sendOrderToServer(order);

        }



    }


    private void sendOrderToServer(Order order){
        order.setCartItems(CartHelper.getCart().getCartItems());
        CartHelper.getCart().clear();

        Log.d(TAG, "Order = " + order);
        getMvpView().goToOrderResult();
    }

    private boolean validateAndShowErrors(Order order) {

        Validator validator = Validator.ValidatorBuilder.getInstance()
                .addField(order.getStreet(), PlaceOrderContract.Field.STREET, Validator.Rule.NOT_EMPTY)
                .addField(order.getHouse(), PlaceOrderContract.Field.HOUSE, Validator.Rule.NOT_EMPTY)
                .addField(order.getFlat(), PlaceOrderContract.Field.FLAT, Validator.Rule.NOT_EMPTY)
                .addField(order.getPhone(), PlaceOrderContract.Field.PHONE, Validator.Rule.NOT_EMPTY)
                .addField(order.getEmail(), PlaceOrderContract.Field.EMAIL, Validator.Rule.NOT_EMPTY)
                .addField(order.getDeliveryTime(), PlaceOrderContract.Field.DELIVERY_TIME, Validator.Rule.NOT_EMPTY)
                .build();
        boolean isValid =  validator.validate();
        if(!isValid) {
            getMvpView().showErrors(validator.getResults());
        }

        return isValid;
    }






}

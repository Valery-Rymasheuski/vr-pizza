package com.example.rymasheuski.valery.vrpizza.placeorder;

import com.example.rymasheuski.valery.vrpizza.base.BaseMvpContract;
import com.example.rymasheuski.valery.vrpizza.util.Validator;

import java.util.List;


/**
 * Created by valery on 20.8.18.
 */

public interface PlaceOrderContract  {

    enum Field{STREET, HOUSE, FLAT, PHONE, EMAIL, DELIVERY_TIME}

    interface MvpView extends BaseMvpContract.MvpView{

        Order getFormData();

        void goToOrderResult();


        void showErrors(List<Validator.ValidationResult> results);

    }


    interface MvpPresenter extends BaseMvpContract.MvpPresenter<MvpView>{




        void onSend();

    }
}

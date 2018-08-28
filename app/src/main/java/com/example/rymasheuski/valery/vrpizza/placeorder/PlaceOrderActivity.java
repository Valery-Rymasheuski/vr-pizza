package com.example.rymasheuski.valery.vrpizza.placeorder;


import android.content.Intent;
import android.databinding.DataBindingUtil;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.rymasheuski.valery.vrpizza.OrderResultActivity;
import com.example.rymasheuski.valery.vrpizza.R;

import com.example.rymasheuski.valery.vrpizza.component.SpinnerComponent;
import com.example.rymasheuski.valery.vrpizza.databinding.ActivityPlaceOrderBinding;

import com.example.rymasheuski.valery.vrpizza.util.InjectionUtil;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;




public class PlaceOrderActivity extends AppCompatActivity {

    private static final String TAG = PlaceOrderActivity.class.getName();

    private PlaceOrderViewModel mPlaceOrderViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPlaceOrderBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_place_order);

        mPlaceOrderViewModel = InjectionUtil.getViewModel(this, PlaceOrderViewModel.class);
        mPlaceOrderViewModel.getOpenResultEvent().observe(this, aVoid -> goToOrderResult());

        binding.setViewModel(mPlaceOrderViewModel);
        binding.setHandler(getHandler());

        UiUtil.prepareToolbar(this, true, R.string.place_order_title);

        SpinnerComponent spinnerComponent = new SpinnerComponent(this, R.id.spinner_city, R.array.cities_array, R.array.city_id_array);
        SpinnerComponent paymentSpinner = new SpinnerComponent(this, R.id.spinner_payment_type, R.array.payment_type_array, R.array.payment_type_id_array);

        spinnerComponent.setObservableField(mPlaceOrderViewModel.cityId);
        paymentSpinner.setObservableField(mPlaceOrderViewModel.paymentTypeId);

    }

    public PlaceOrderHandler getHandler(){
        return (v) -> mPlaceOrderViewModel.send();
    }


    public void goToOrderResult() {
        Intent intent = new Intent(this, OrderResultActivity.class);
        startActivity(intent);
    }

}

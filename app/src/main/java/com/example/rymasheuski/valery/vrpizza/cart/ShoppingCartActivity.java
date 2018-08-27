package com.example.rymasheuski.valery.vrpizza.cart;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.rymasheuski.valery.vrpizza.R;

import com.example.rymasheuski.valery.vrpizza.databinding.ActivityShoppingCartBinding;

import com.example.rymasheuski.valery.vrpizza.placeorder.PlaceOrderActivity;
import com.example.rymasheuski.valery.vrpizza.util.InjectionUtil;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;


public class ShoppingCartActivity extends AppCompatActivity   {

    private ShoppingCartViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShoppingCartBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_cart);

        mViewModel = InjectionUtil.getViewModel(this, ShoppingCartViewModel.class);
        binding.setViewModel(mViewModel);
        binding.setHandler(getHandler());


        UiUtil.prepareToolbar(this, true, R.string.shopping_cart_title);

        RecyclerView recyclerView = findViewById(R.id.shopping_cart_items);
        CartItemRecyclerAdapter adapter = new CartItemRecyclerAdapter(mViewModel);
        recyclerView.setAdapter(adapter);
    }


    public void goToPlaceOrder() {
        Intent intent = new Intent(getApplicationContext(), PlaceOrderActivity.class);
        startActivity(intent);
    }

    private ShoppingCartHandler getHandler(){
        return new ShoppingCartHandler() {
            @Override
            public void onClearOrder(View view) {
                mViewModel.onClearOrder();
            }

            @Override
            public void onOrder(View view) {
                    goToPlaceOrder();
            }
        };
    }


}

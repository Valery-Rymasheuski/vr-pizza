package com.example.rymasheuski.valery.vrpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        RecyclerView recyclerView = findViewById(R.id.shopping_cart_items);
        CartItemRecyclerAdapter adapter = new CartItemRecyclerAdapter();
        recyclerView.setAdapter(adapter);


        Button clearOrderButton = findViewById(R.id.button_clear_order);
        clearOrderButton.setOnClickListener(v -> {
            CartHelper.getCart().clear();
            adapter.loadCartItems();
            adapter.notifyDataSetChanged();
        });

        Button orderButton = findViewById(R.id.button_order);
        orderButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PlaceOrderActivity.class);
            startActivity(intent);
        });


        UiUtil.prepareToolbar(this, true, R.string.shopping_cart_title);
    }
}

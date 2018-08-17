package com.example.rymasheuski.valery.vrpizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FoodOptionsHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity implements CartItemRecyclerAdapter.ProductQuantityListener {

    private TextView mTotalTextView;
    private Button mOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        updateOptionsInCart();


        RecyclerView recyclerView = findViewById(R.id.shopping_cart_items);
        CartItemRecyclerAdapter adapter = new CartItemRecyclerAdapter();
        adapter.setQuantityListener(this);
        recyclerView.setAdapter(adapter);


        Button clearOrderButton = findViewById(R.id.button_clear_order);
        clearOrderButton.setOnClickListener(v -> {
            CartHelper.getCart().clear();
            adapter.loadCartItems();
            adapter.notifyDataSetChanged();
            calcAndShowTotal();
        });

        mOrderButton = findViewById(R.id.button_order);
        mOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PlaceOrderActivity.class);
            startActivity(intent);
        });


        UiUtil.prepareToolbar(this, true, R.string.shopping_cart_title);


        mTotalTextView = findViewById(R.id.tv_order_total);
        calcAndShowTotal();
    }

    private void updateOptionsInCart(){
        List<CartItem> cartItems = CartHelper.getCart().getCartItems();
        for(CartItem cartItem : cartItems){
            Long productId = cartItem.getProduct().getId();

            FoodOption sizeOption = FoodOptionsHelper.sizeOptionHelper.getOption(productId);
            FoodOption pizzaOption = FoodOptionsHelper.pizzaOptionHelper.getOption(productId);

            cartItem.clearSelectedOptions();
            cartItem.addSelectedOption(sizeOption);
            cartItem.addSelectedOption(pizzaOption);


        }
    }


    private void calcAndShowTotal(){
        List<CartItem> items = CartHelper.getCart().getCartItems();
        int total = 0;
        for(CartItem item : items){

            total += item.getQuantity() * item.getPriceWithOptions();

        }



        String text = getString(R.string.format_order_total, FormatUtil.formatPrice(total, this));
        mTotalTextView.setText(text);


        mOrderButton.setEnabled(total > 0);


    }




    @Override
    public void onChangeQuantity(Saleable product, int quantity) {
        calcAndShowTotal();
    }
}

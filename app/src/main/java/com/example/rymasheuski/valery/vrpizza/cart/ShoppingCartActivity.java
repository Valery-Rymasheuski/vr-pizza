package com.example.rymasheuski.valery.vrpizza.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.placeorder.PlaceOrderActivity;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;

import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity
        implements ShoppingCartContract.MvpView{

    private TextView mTotalTextView;
    private Button mOrderButton;
    private CartItemRecyclerAdapter mAdapter;
    private ShoppingCartPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        UiUtil.prepareToolbar(this, true, R.string.shopping_cart_title);

        RecyclerView recyclerView = findViewById(R.id.shopping_cart_items);
        mAdapter = new CartItemRecyclerAdapter();
        mAdapter.setQuantityListener( (p, q) -> mPresenter.onChangeQuantity());
        recyclerView.setAdapter(mAdapter);

        Button clearOrderButton = findViewById(R.id.button_clear_order);
        clearOrderButton.setOnClickListener(v ->  mPresenter.onClearOrderClicked());

        mOrderButton = findViewById(R.id.button_order);
        mOrderButton.setOnClickListener(v -> mPresenter.onOrderClicked());

        mTotalTextView = findViewById(R.id.tv_order_total);

        mPresenter = new ShoppingCartPresenter(this);
        mPresenter.onViewIsReady();

    }

    @Override
    public void setCartItems(List<CartItem> cartItems) {
        mAdapter.clearAndAddAll(cartItems);
    }

    @Override
    public void goToPlaceOrder() {
        Intent intent = new Intent(getApplicationContext(), PlaceOrderActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTotal(int total) {
        String text = getString(R.string.format_order_total, FormatUtil.formatPrice(total, this));
        mTotalTextView.setText(text);

        mOrderButton.setEnabled(total > 0);
    }


}

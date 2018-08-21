package com.example.rymasheuski.valery.vrpizza.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.base.recycler.MvpRecyclerAdapter;
import com.example.rymasheuski.valery.vrpizza.base.recycler.MvpViewHolder;
import com.example.rymasheuski.valery.vrpizza.component.OrderCountComponent;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 13.8.18.
 */

public class CartItemRecyclerAdapter extends MvpRecyclerAdapter<CartItem,
        CartItemViewHolderPresenter, CartItemRecyclerAdapter.CartItemViewHolder> {

    private ProductQuantityListener mQuantityListener;

    CartItemRecyclerAdapter() {

    }


    @NonNull
    @Override
    protected CartItemViewHolderPresenter createPresenter(@NonNull CartItem model) {
        CartItemViewHolderPresenter presenter = new CartItemViewHolderPresenter();
        presenter.setModel(model);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull CartItem model) {
        return model.getProduct().getId();
    }


    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }




    void setQuantityListener(ProductQuantityListener quantityListener) {
        mQuantityListener = quantityListener;
    }

    class CartItemViewHolder extends MvpViewHolder<CartItemViewHolderContract.MvpPresenter> implements CartItemViewHolderContract.MvpView {

        final View mView;
        final TextView mNameView;
        final TextView mOptionView;
        final TextView mDescView;
        final TextView mPriceView;
        final TextView mWeightView;
        final OrderCountComponent mOrderCountComponent;


        CartItemViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.tv_cart_item_name);
            mPriceView = view.findViewById(R.id.tv_food_price);
            mDescView = view.findViewById(R.id.tv_cart_item_desc);
            mOptionView = view.findViewById(R.id.tv_cart_item_options);
            mWeightView = view.findViewById(R.id.tv_food_size);
            mOrderCountComponent = OrderCountComponent.ComponentBuilder.getInstance(view)
                    .withDefaultIds()
                    .build();
        }


        @Override
        public void setProductInfo(String name, String desc, String options) {
            mNameView.setText(name);
            mDescView.setText(desc);
            mOptionView.setText(options);
        }

        @Override
        public void setWeightAndPrice(int weight, int price) {
            Context context = mView.getContext();


            mWeightView.setText(FormatUtil.formatSize(weight, context));
            mPriceView.setText(FormatUtil.formatPrice(price, context));
        }

        @Override
        public void setQuantity(int quantity) {
            mOrderCountComponent.init(quantity);
            mOrderCountComponent.setOnChangeListener(value -> mPresenter.onQuantityChanged(value));
        }

        @Override
        public void callQuantityListener(Saleable product, int quantity) {
            if(mQuantityListener != null) {
                mQuantityListener.onChangeQuantity(product, quantity);
            }
        }
    }


    interface ProductQuantityListener {
        void onChangeQuantity(Saleable product, int quantity);
    }

}

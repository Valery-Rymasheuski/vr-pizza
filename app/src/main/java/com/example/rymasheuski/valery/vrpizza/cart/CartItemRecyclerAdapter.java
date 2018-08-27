package com.example.rymasheuski.valery.vrpizza.cart;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseRecyclerAdapter;
import com.example.rymasheuski.valery.vrpizza.component.OrderCountComponent;
import com.example.rymasheuski.valery.vrpizza.databinding.CartItemBinding;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;


/**
 * Created by valery on 13.8.18.
 */

public class CartItemRecyclerAdapter extends BaseRecyclerAdapter<CartItem, CartItemRecyclerAdapter.CartItemViewHolder> {


    private static final String TAG = CartItemRecyclerAdapter.class.getName();
    private ShoppingCartViewModel mViewModel;


    CartItemRecyclerAdapter(ShoppingCartViewModel viewModel) {
        mViewModel = viewModel;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartItemBinding binding = CartItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new CartItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = getItem(position);
        holder.mBinding.setItem(item);


        holder.mOrderCountComponent.init(item.getQuantity());
        holder.mOrderCountComponent.setOnChangeListener(value ->
                mViewModel.onQuantityChanged(item.getProduct(), value));

    }



    class CartItemViewHolder extends RecyclerView.ViewHolder{

        final View mView;
        final CartItemBinding mBinding;
        final OrderCountComponent mOrderCountComponent;


        CartItemViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mView = binding.getRoot();

            mOrderCountComponent = OrderCountComponent.ComponentBuilder.getInstance(mView)
                    .withDefaultIds()
                    .build();
        }

    }
}

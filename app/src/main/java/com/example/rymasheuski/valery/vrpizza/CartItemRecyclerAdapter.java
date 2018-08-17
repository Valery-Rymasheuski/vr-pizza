package com.example.rymasheuski.valery.vrpizza;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.component.OrderCountComponent;
import com.example.rymasheuski.valery.vrpizza.model.CartItem;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;
import com.example.rymasheuski.valery.vrpizza.util.Saleable;

import java.util.List;

/**
 * Created by valery on 13.8.18.
 */

public class CartItemRecyclerAdapter extends RecyclerView.Adapter<CartItemRecyclerAdapter.CartItemViewHolder> {

    private List<CartItem> mCartItems;

    private ProductQuantityListener mQuantityListener;


    public CartItemRecyclerAdapter() {
        loadCartItems();
    }


    public void loadCartItems(){
        mCartItems = CartHelper.getCart().getCartItems();
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = mCartItems.get(position);
        Context context = holder.mView.getContext();

        holder.mNameView.setText(item.getProduct().getName());
        holder.mDescView.setText(item.getProduct().getDescription());
        holder.mOptionView.setText(item.getOptionsLabel());
        holder.mWeightView.setText(FormatUtil.formatSize(item.getWeightWithOptions(), context));
        holder.mPriceView.setText(FormatUtil.formatPrice(item.getPriceWithOptions(), context));

        holder.mOrderCountComponent.init(item.getQuantity());
        holder.mOrderCountComponent.setOnChangeListener(value -> {
            CartHelper.getCart().addProduct(item.getProduct(), value);
            if(mQuantityListener != null){
                mQuantityListener.onChangeQuantity(item.getProduct(), value);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mCartItems.size();
    }


    public void setQuantityListener(ProductQuantityListener mQuantityListener) {
        this.mQuantityListener = mQuantityListener;
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        public final TextView mNameView;
        public final TextView mOptionView;
        public final TextView mDescView;
        public final TextView mPriceView;
        public final TextView mWeightView;
        public final OrderCountComponent mOrderCountComponent;


        public CartItemViewHolder(View view) {
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
    }


    public interface ProductQuantityListener{


        public void onChangeQuantity(Saleable product, int quantity);

    }
}

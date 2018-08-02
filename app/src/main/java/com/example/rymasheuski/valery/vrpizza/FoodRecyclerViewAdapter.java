package com.example.rymasheuski.valery.vrpizza;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rymasheuski.valery.vrpizza.FoodFragment.OnListFragmentInteractionListener;
import com.example.rymasheuski.valery.vrpizza.dummy.DummyContent.DummyItem;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.Pizza;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.PizzaViewHolder> {

    private final List<Food> mFoodList;
    private final OnListFragmentInteractionListener mListener;

    public FoodRecyclerViewAdapter(List<Food> foodList, OnListFragmentInteractionListener listener) {
        mFoodList = foodList;
        mListener = listener;
    }

    @Override
    public PizzaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_food_item, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PizzaViewHolder holder, int position) {
        Food food = mFoodList.get(position);
        holder.mItem = food;
        holder.mNameView.setText(food.getName());
        holder.mDescView.setText(food.getDescription());
        Context context = holder.mView.getContext();
        holder.mPriceView.setText(FormatUtil.formatPrice(food.getPrice(), context));
        holder.mSizeView.setText(FormatUtil.formatSize(food.getSize(), context));
        if(food.getImageId() != null) {
            holder.mImageView.setImageResource(food.getImageId());
        }

        holder.mButton.setOnClickListener(v ->{
            String text = context.getString(R.string.to_cart_success, food.getName());
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class PizzaViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDescView;
        public final TextView mPriceView;
        public final TextView mSizeView;
        public final ImageView mImageView;
        public final Button mButton;
        public Food mItem;

        public PizzaViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.tv_food_name);
            mDescView = view.findViewById(R.id.tv_food_desc);
            mPriceView = view.findViewById(R.id.tv_food_price);
            mSizeView = view.findViewById(R.id.tv_food_size);
            mImageView = view.findViewById(R.id.image_view_food);
            mButton = view.findViewById(R.id.button_food_to_cart);
        }


    }
}

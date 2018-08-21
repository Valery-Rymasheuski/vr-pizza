package com.example.rymasheuski.valery.vrpizza.menu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.base.recycler.MvpRecyclerAdapter;
import com.example.rymasheuski.valery.vrpizza.base.recycler.MvpViewHolder;
import com.example.rymasheuski.valery.vrpizza.component.FoodOptionsComponent;
import com.example.rymasheuski.valery.vrpizza.component.OrderCountComponent;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;
import com.example.rymasheuski.valery.vrpizza.util.FormatUtil;

/**
 *

 */
public class FoodRecyclerViewAdapter extends MvpRecyclerAdapter<Food, FoodViewHolderPresenter, FoodRecyclerViewAdapter.FoodViewHolder> {



    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_food_item, parent, false);
        return new FoodViewHolder(view);
    }


    @NonNull
    @Override
    protected FoodViewHolderPresenter createPresenter(@NonNull Food model) {
        FoodViewHolderPresenter presenter = new FoodViewHolderPresenter();
        presenter.setModel(model);
        return presenter;

    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull Food model) {
        return model.getId();
    }


    public class FoodViewHolder extends MvpViewHolder<FoodViewHolderContract.MvpPresenter> implements FoodViewHolderContract.MvpView {
        final View mView;
        final TextView mNameView;
        final TextView mDescView;
        final TextView mPriceView;
        final TextView mSizeView;
        final ImageView mImageView;
        final Button mButton;
        final FoodOptionsComponent mOptionSizesComponent;
        final FoodOptionsComponent mOptionPizzaComponent;
        final OrderCountComponent mOrderCountComponent;



        FoodViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = view.findViewById(R.id.tv_food_name);
            mDescView = view.findViewById(R.id.tv_food_desc);
            mPriceView = view.findViewById(R.id.tv_food_price);
            mSizeView = view.findViewById(R.id.tv_food_size);
            mImageView = view.findViewById(R.id.image_view_food);
            mButton = view.findViewById(R.id.button_food_to_cart);
            mOptionSizesComponent = FoodOptionsComponent.createOptionSizes(view, R.id.stub_food_option_sizes);
            mOptionPizzaComponent = FoodOptionsComponent.createPizzaOptions(view, R.id.stub_food_option_pizza);


            mOrderCountComponent = OrderCountComponent.ComponentBuilder.getInstance(view)
                    .withDefaultIds()
                    .build();
        }

        @Override
        public void setFoodInfo(Food food) {

            mNameView.setText(food.getName());
            mDescView.setText(food.getDescription());

            if(food.getImageId() != null) {
                mImageView.setImageResource(food.getImageId());
            }

        }

        @Override
        public void setOptions(Long foodId, FoodOption sizeOption, FoodOption pizzaOption) {

            mOptionSizesComponent.setSelectedOptionListener(option -> {
                mPresenter.onSizeOptionChanged(foodId, option);
            });
            mOptionSizesComponent.show(sizeOption);

            mOptionPizzaComponent.setSelectedOptionListener(option -> {
                mPresenter.onPizzaOptionChanged(foodId, option);
            });
            mOptionPizzaComponent.show(pizzaOption);
        }


        @Override
        public void setPriceAndWeight(int price, int weight) {
            Context context = mView.getContext();

            mPriceView.setText(FormatUtil.formatPrice(price, context));
            mSizeView.setText(FormatUtil.formatSize(weight, context));
        }

        @Override
        public void setQuantity(int quantity) {
            mOrderCountComponent.init(quantity);
            mOrderCountComponent.setOnChangeListener(value ->{
                mPresenter.onQuantityChanged(value);
            });
        }
    }



}

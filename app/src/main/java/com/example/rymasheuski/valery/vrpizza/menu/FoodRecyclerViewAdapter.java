package com.example.rymasheuski.valery.vrpizza.menu;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.base.recycler.BaseRecyclerAdapter;
import com.example.rymasheuski.valery.vrpizza.component.FoodOptionsComponent;
import com.example.rymasheuski.valery.vrpizza.component.OrderCountComponent;
import com.example.rymasheuski.valery.vrpizza.databinding.FoodItemBinding;


/**
 *

 */
public class FoodRecyclerViewAdapter extends BaseRecyclerAdapter<FoodViewModel, FoodRecyclerViewAdapter.FoodViewHolder> {



    @Override
    @NonNull
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.food_item, parent, false);

        return new FoodViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodViewModel foodViewModel = getItem(position);
        holder.mBinding.setViewModel(foodViewModel);

        setOptions(holder, foodViewModel);
        setQuantity(holder, foodViewModel);


    }

    private void setOptions(FoodViewHolder holder, FoodViewModel foodViewModel) {
        if(foodViewModel.isContainsOptions()) {
            holder.mOptionSizesComponent.show(foodViewModel.sizeOption);
            holder.mOptionPizzaComponent.show(foodViewModel.pizzaOption);
        }
    }



    private void setQuantity(FoodViewHolder holder, FoodViewModel foodViewModel) {
        holder.mOrderCountComponent.init(foodViewModel.quantity);

    }


    public class FoodViewHolder extends RecyclerView.ViewHolder  {

        final FoodItemBinding mBinding;

        final FoodOptionsComponent mOptionSizesComponent;
        final FoodOptionsComponent mOptionPizzaComponent;
        final OrderCountComponent mOrderCountComponent;



        FoodViewHolder(FoodItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            View view = binding.getRoot();

            mOptionSizesComponent = FoodOptionsComponent.createOptionSizes(view, R.id.stub_food_option_sizes);
            mOptionPizzaComponent = FoodOptionsComponent.createPizzaOptions(view, R.id.stub_food_option_pizza);

            mOrderCountComponent = OrderCountComponent.ComponentBuilder.getInstance(view)
                    .withDefaultIds()
                    .build();
        }
    }

}

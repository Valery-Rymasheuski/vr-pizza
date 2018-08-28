package com.example.rymasheuski.valery.vrpizza.menu;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.databinding.FragmentFoodListBinding;
import com.example.rymasheuski.valery.vrpizza.util.InjectionUtil;


/**
 * A fragment representing a list of Items.

 */
public class FoodListFragment extends Fragment {


    private static final String ARG_TAB_INDEX = "food_tab_index";

    private int mTabIndex;




    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FoodListFragment() {
    }


    public static FoodListFragment newInstance(int tabIndex) {
        FoodListFragment fragment = new FoodListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TAB_INDEX, tabIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTabIndex = getArguments().getInt(ARG_TAB_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFoodListBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                 R.layout.fragment_food_list, container, false);

        View view = binding.getRoot();
        FoodListViewModel viewModel = InjectionUtil.getViewModel(this, mTabIndex, FoodListViewModel.class);
        viewModel.init(mTabIndex);

        binding.setViewModel(viewModel);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setAdapter(new FoodRecyclerViewAdapter());
        }
        return view;
    }
}

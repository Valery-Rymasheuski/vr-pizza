package com.example.rymasheuski.valery.vrpizza.menu;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.model.Food;

import java.util.List;

/**
 * A fragment representing a list of Items.

 */
public class FoodListFragment extends Fragment implements  FoodListContract.MvpView {


    private static final String ARG_TAB_INDEX = "food_tab_index";

    private FoodListPresenter mPresenter;

    private int mTabIndex;

    private FoodRecyclerViewAdapter mAdapter;


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

        mPresenter = new FoodListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            mAdapter = new FoodRecyclerViewAdapter();
            recyclerView.setAdapter(mAdapter);

            mPresenter.onViewIsReady();
        }
        return view;
    }

    @Override
    public void showList(List<Food> list) {
        mAdapter.clearAndAddAll(list);
    }

    @Override
    public int getTabIndex() {
        return mTabIndex;
    }
}

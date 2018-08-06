package com.example.rymasheuski.valery.vrpizza.component;

import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.example.rymasheuski.valery.vrpizza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 3.8.18.
 */

public class FoodOptionsComponent {

    private enum ComponentType {OPTION_SIZE, OPTION_PIZZA};

    private ViewStub mViewStub;
    private View mRootView;
    private View mViewContainer;
    private int mContainerId;
    private ComponentType mType;

    private FoodOptionsComponent(View rootView, int containerId, ComponentType type) {
        mContainerId = containerId;
        mRootView = rootView;
        mType = type;
        mViewStub = rootView.findViewById(containerId);
    }


    public static FoodOptionsComponent createOptionSizes(View rootView, int containerId){
        return new FoodOptionsComponent(rootView, containerId, ComponentType.OPTION_SIZE);


    }


    public static FoodOptionsComponent createPizzaOptions(View rootView, int containerId){
        return new FoodOptionsComponent(rootView, containerId, ComponentType.OPTION_PIZZA);
    }


    public void show(){
        if(mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
            mViewStub = null;
        }

        mViewContainer = mRootView.findViewById(mContainerId);
        List<TextView> textViewList = getTextViews(mViewContainer);
        String labels[] = getLabels();

        for(int i = 0; i < labels.length; i++){
            TextView textView = textViewList.get(i);  //TODO fix NUllPointer
            textView.setText(labels[i]);
            textView.setOnClickListener(v -> {
                for(TextView optionView : textViewList){
                    optionView.setSelected(false);
                }
                v.setSelected(true);
            });
        }

        if(!isSelectedExists(textViewList)) {
            textViewList.get(0).setSelected(true);
        }

    }

    private boolean isSelectedExists(List<TextView> textViewList){
        for(TextView optionView : textViewList){
            if(optionView.isSelected()){
                return true;
            }
        }
        return false;
    }

    private String[] getLabels(){
        Context context = mRootView.getContext();


        switch (mType){
            case OPTION_SIZE:
                int optionSizeLabels[] = context.getResources().getIntArray(R.array.option_sizes);
                String labels[] = new String[optionSizeLabels.length];
                for(int i = 0; i <  optionSizeLabels.length; i++){
                    String label = context.getString(R.string.option_size_format, optionSizeLabels[i]);
                    labels[i] = label;
                }
                return labels;
            case OPTION_PIZZA:
                return  context.getResources().getStringArray(R.array.option_pizza);

            default:
                throw new IllegalArgumentException("Unknown component type: " + mType);
        }


    }




    private static List<TextView> getTextViews(View view){
        List<TextView> optionTextViewList = new ArrayList<>();
        optionTextViewList.add(view.findViewById(R.id.food_option_first));
        optionTextViewList.add(view.findViewById(R.id.food_option_second));
        optionTextViewList.add(view.findViewById(R.id.food_option_third));
        return optionTextViewList;
    }
}

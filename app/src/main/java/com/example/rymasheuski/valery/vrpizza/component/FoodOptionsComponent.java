package com.example.rymasheuski.valery.vrpizza.component;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.model.FoodOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 3.8.18.
 */

public class FoodOptionsComponent {

    private static final int OPTION_COUNT = 3;

    private enum ComponentType {OPTION_SIZE, OPTION_PIZZA};

    private ViewStub mViewStub;
    private View mRootView;
    private View mViewContainer;
    private int mContainerId;
    private ComponentType mType;
    private OnSelectedOptionListener mSelectedOptionListener;
    private FoodOption mFoodOptions[];



    private FoodOptionsComponent(View rootView, int containerId, ComponentType type) {
        mContainerId = containerId;
        mRootView = rootView;
        mType = type;
        mViewStub = rootView.findViewById(containerId);
        mFoodOptions = getOptions();
    }


    public static FoodOptionsComponent createOptionSizes(View rootView, int containerId){
        return new FoodOptionsComponent(rootView, containerId, ComponentType.OPTION_SIZE);


    }


    public static FoodOptionsComponent createPizzaOptions(View rootView, int containerId){
        return new FoodOptionsComponent(rootView, containerId, ComponentType.OPTION_PIZZA);
    }

    public void setSelectedOptionListener(OnSelectedOptionListener selectedOptionListener) {
        this.mSelectedOptionListener = selectedOptionListener;
    }

    public void show(FoodOption selectedOption){
        if(mViewStub != null) {
            mViewStub.setVisibility(View.VISIBLE);
            mViewStub = null;
        }

        mViewContainer = mRootView.findViewById(mContainerId);
        TextView textViews[] = getTextViews(mViewContainer);


        if(mFoodOptions.length != textViews.length){
            throw new IllegalArgumentException("Invalid array sizes");
        }


        SparseArray<FoodOption> textViewIdToOptionArray = new SparseArray<>(OPTION_COUNT);

        FoodOption iterOption;
        for(int i = 0; i < mFoodOptions.length; i++){
            TextView textView = textViews[i];
            iterOption = mFoodOptions[i];
            textView.setText(iterOption.getLabel());

            textViewIdToOptionArray.put(textView.getId(), iterOption);

            textView.setOnClickListener(v -> {
                unselectAll(textViews);

                v.setSelected(true);
                if(mSelectedOptionListener != null){
                    mSelectedOptionListener.onSelectOption(textViewIdToOptionArray.get(v.getId()));
                }

            });
        }

        selectOption(selectedOption, mFoodOptions, textViews);

    }

    private void unselectAll(TextView textViews[]){
        for(TextView optionView : textViews){
            optionView.setSelected(false);
        }
    }

    private void selectOption(FoodOption selectedOption, FoodOption options[], TextView textViews[]){
        int selectedOptionIndex = 0;
        if(selectedOption != null){
            for(int i = 0; i < options.length; i++){
                if(selectedOption.getId() == options[i].getId()){
                    selectedOptionIndex = i;
                    break;
                }
            }
        }else{
            if(mSelectedOptionListener != null){
                mSelectedOptionListener.onSelectOption(options[selectedOptionIndex]);
            }
        }

        unselectAll(textViews);

        textViews[selectedOptionIndex].setSelected(true);

    }


    private FoodOption[] getOptions(){
        Context context = mRootView.getContext();
        int idsResource;
        int percentsResource;
        switch (mType){
            case OPTION_SIZE:
                idsResource = R.array.option_size_id_array;
                percentsResource = R.array.option_size_percent_array;
                break;

            case OPTION_PIZZA:
                idsResource = R.array.option_pizza_id_array;
                percentsResource = R.array.option_pizza_percent_array;
                break;
            default:
                throw new IllegalArgumentException("Unknown component type: " + mType);
        }

        int optionIds[] = context.getResources().getIntArray(idsResource);
        int optionPercents[] = context.getResources().getIntArray(percentsResource);


        String labels[] = getLabels();

        if(optionIds.length != optionPercents.length && optionIds.length != labels.length){
            throw new IllegalArgumentException("Invalid array sizes");
        }

        FoodOption options[] = new FoodOption[optionIds.length];
        for(int i = 0; i < optionIds.length; i++){
            options[i] = new FoodOption(optionIds[i], labels[i], optionPercents[i]);
        }
        return options;
    }


    private String[] getLabels(){
        Context context = mRootView.getContext();


        switch (mType){
            case OPTION_SIZE:
                int optionSizeLabels[] = context.getResources().getIntArray(R.array.option_size_labels);
                String labels[] = new String[optionSizeLabels.length];
                for(int i = 0; i <  optionSizeLabels.length; i++){
                    String label = context.getString(R.string.option_size_format, optionSizeLabels[i]);
                    labels[i] = label;
                }
                return labels;
            case OPTION_PIZZA:
                return  context.getResources().getStringArray(R.array.option_pizza_labels);

            default:
                throw new IllegalArgumentException("Unknown component type: " + mType);
        }


    }




    private static TextView[] getTextViews(View view){
        TextView optionTextViews[] = new TextView[OPTION_COUNT];
        optionTextViews[0] = view.findViewById(R.id.food_option_first);
        optionTextViews[1] = view.findViewById(R.id.food_option_second);
        optionTextViews[2] = view.findViewById(R.id.food_option_third);
        return optionTextViews;
    }


    public interface OnSelectedOptionListener {

        void onSelectOption(FoodOption option);
    }
}

package com.example.rymasheuski.valery.vrpizza.component;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by valery on 3.8.18.
 */

public class OrderCountComponent {

    private TextView mLeftTextView;
    private TextView mCenterTextView;
    private TextView mRightTextView;

    private View mAlternateView;


    private OrderCountComponent() {
    }



    public void init(){
        mLeftTextView.setOnClickListener(v -> {
            updateValue(false);
        });

        mRightTextView.setOnClickListener(v -> {
            updateValue(true);
        });


        checkVisibility();
    }


    public void addOne(){
        updateValue(true);
        checkVisibility();
    }


    private  void checkVisibility(){
        int value = getValue();
        if(value == 0){
            mAlternateView.setVisibility(View.VISIBLE);
            mLeftTextView.setVisibility(View.GONE);
            mCenterTextView.setVisibility(View.GONE);
            mRightTextView.setVisibility(View.GONE);
        }else{
            mAlternateView.setVisibility(View.GONE);
            mLeftTextView.setVisibility(View.VISIBLE);
            mCenterTextView.setVisibility(View.VISIBLE);
            mRightTextView.setVisibility(View.VISIBLE);
        }
    }

    private int getValue(){
        CharSequence text = mCenterTextView.getText();
        return Integer.parseInt(text.toString());
    }


    private void updateValue(boolean isAddition){
        int value = getValue();
        if(isAddition){
            value = value + 1;
        }else if(value > 0) {
            value = value - 1;

        }
        mCenterTextView.setText(String.valueOf(value));
        if(!isAddition){
            checkVisibility();
        }
    }

    public static class ComponentBuilder{

        private Integer mLeftId;
        private Integer mCenterId;
        private Integer mRightId;
        private View mRootView;
        private Integer mAlternateId;

        private ComponentBuilder() {
        }

        public static ComponentBuilder getInstance(View rootView) {
            ComponentBuilder builder = new ComponentBuilder();
            builder.mRootView = rootView;
            return builder;
        }


        public ComponentBuilder withLeftViewId(int leftViewId){
            this.mLeftId = leftViewId;
            return this;
        }

        public ComponentBuilder withCenterViewId(int centerViewId){
            this.mCenterId = centerViewId;
            return this;
        }

        public ComponentBuilder withRightViewId(int rightViewId){
            this.mRightId = rightViewId;
            return this;
        }

        public ComponentBuilder withAlternateViewId(int alternateViewId){
            this.mAlternateId = alternateViewId;
            return this;
        }


        public OrderCountComponent build(){
            if(mRootView == null || mRightId == null || mCenterId == null
                    || mLeftId == null || mAlternateId == null){
                throw new NullPointerException();
            }
            OrderCountComponent component = new OrderCountComponent();
            component.mLeftTextView = mRootView.findViewById(mLeftId);
            component.mRightTextView = mRootView.findViewById(mRightId);
            component.mCenterTextView = mRootView.findViewById(mCenterId);
            component.mAlternateView = mRootView.findViewById(mAlternateId);

            return component;
        }

    }

}

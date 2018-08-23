package com.example.rymasheuski.valery.vrpizza.util;

import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.rymasheuski.valery.vrpizza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 16.8.18.
 */

public class Validator {

    private String mEmptyErrorMessage;

    private List<FieldInfo> mFieldList = new ArrayList<>();




    public enum Rule {
        NOT_EMPTY
    }


    private Validator() {
    }


    public boolean validate(){
        boolean isValid = true;
        String text;

        for(FieldInfo fieldInfo : mFieldList){
            fieldInfo.getError().set(null);
            text = fieldInfo.getValue();
            for(Rule rule : fieldInfo.getRules()){
                switch (rule){
                    case NOT_EMPTY:
                        if(TextUtils.isEmpty(text)){
                            fieldInfo.getError().set(mEmptyErrorMessage);
                            isValid = false;
                        }
                        break;

                }
            }


        }
        return isValid;
    }







    public static class ValidatorBuilder {

        private Validator mValidator = new Validator();


        private ValidatorBuilder() {

        }

        public static ValidatorBuilder getInstance(){
            return new ValidatorBuilder();
        }

        public ValidatorBuilder addField(String value, ObservableField<String> error, Rule... rules){
            mValidator.mFieldList.add(new FieldInfo(value, error, rules));
            return this;
        }

        public ValidatorBuilder setEmptyErrorMessage(String msg){
            mValidator.mEmptyErrorMessage = msg;
            return this;
        }

        public Validator build(){
            return mValidator;
        }


    }


    private static class FieldInfo{
        private String mValue;
        private ObservableField<String> mError;
        private Rule mRules[];

        FieldInfo(String value, ObservableField<String> error, Rule[] rules) {
            this.mValue = value;
            this.mError = error;
            this.mRules = rules;
        }

        public String getValue() {
            return mValue;
        }

        public Rule[] getRules() {
            return mRules;
        }

        public ObservableField<String> getError() {
            return mError;
        }
    }



}

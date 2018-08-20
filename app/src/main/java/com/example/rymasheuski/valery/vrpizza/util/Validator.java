package com.example.rymasheuski.valery.vrpizza.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.rymasheuski.valery.vrpizza.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 16.8.18.
 */

public class Validator {

    private List<FieldInfo> mFieldList = new ArrayList<>();

    private List<ValidationResult> mResults = new ArrayList<>();


    public enum Rule {
        NOT_EMPTY
    }


    private Validator() {
    }


    public boolean validate(){
        boolean isValid = true;
        String text;
        mResults.clear();
        for(FieldInfo fieldInfo : mFieldList){

            text = fieldInfo.getValue();
            for(Rule rule : fieldInfo.getRules()){
                switch (rule){
                    case NOT_EMPTY:
                        if(TextUtils.isEmpty(text)){
                            mResults.add(new ValidationResult(fieldInfo.getFieldCode(), rule));
                            isValid = false;
                        }
                        break;

                }
            }


        }
        return isValid;
    }

    public List<ValidationResult> getResults() {
        return mResults;
    }





    public static class ValidatorBuilder {

        private Validator mValidator = new Validator();


        private ValidatorBuilder() {

        }

        public static ValidatorBuilder getInstance(){
            return new ValidatorBuilder();
        }

        public ValidatorBuilder addField(String value, Object fieldCode, Rule... rules){
            mValidator.mFieldList.add(new FieldInfo(value, fieldCode, rules));
            return this;
        }

        public Validator build(){
            return mValidator;
        }


    }


    private static class FieldInfo{
        private String mValue;
        private Object mFieldCode;
        private Rule mRules[];

        FieldInfo(String value, Object fieldCode, Rule[] rules) {
            this.mValue = value;
            this.mFieldCode = fieldCode;
            this.mRules = rules;
        }

        public String getValue() {
            return mValue;
        }

        public Rule[] getRules() {
            return mRules;
        }

        public Object getFieldCode() {
            return mFieldCode;
        }
    }


    public class ValidationResult {

        private Object mFieldCode;

        private Rule mRule;


        private ValidationResult(Object fieldCode, Rule  rule) {
            this.mFieldCode = fieldCode;
            this.mRule = rule;
        }

        public Object getFieldCode() {
            return mFieldCode;
        }

        public Rule getRule() {
            return mRule;
        }
    }
}

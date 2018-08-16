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

    public static final int ERROR_STRING_ID_IS_EMPTY = R.string.error_is_empty_format;

    private List<FieldInfo> mFieldList = new ArrayList<>();
    private Context mContext;

    public enum Rule {
        NOT_EMPTY
    }


    private Validator() {
    }


    public boolean validate(){
        boolean isValid = true;
        EditText editText;
        for(FieldInfo fieldInfo : mFieldList){
            editText = fieldInfo.getEditText();
            CharSequence text = editText.getText();

            for(Rule rule : fieldInfo.getRules()){
                switch (rule){
                    case NOT_EMPTY:
                        if(TextUtils.isEmpty(text)){


                            setEmptyError(editText);
                            isValid = false;
                        }
                        break;

                }
            }


        }
        return isValid;
    }

    private void setEmptyError(EditText editText){
        CharSequence hint = editText.getHint();

        String error =  mContext.getString(ERROR_STRING_ID_IS_EMPTY, hint);
        editText.setError(error);
    }



    public static class ValidatorBuilder {

        private Validator mValidator = new Validator();


        private ValidatorBuilder(Context context) {
            mValidator.mContext = context;
        }

        public static ValidatorBuilder getInstance(Context context){
            return new ValidatorBuilder(context);
        }

        public ValidatorBuilder addField(EditText editText, Rule... rules){
            mValidator.mFieldList.add(new FieldInfo(editText, rules));
            return this;
        }

        public Validator build(){
            return mValidator;
        }


    }


    private static class FieldInfo{
        private EditText mEditText;
        private Rule mRules[];

        public FieldInfo(EditText editText, Rule[] rules) {
            this.mEditText = editText;
            this.mRules = rules;
        }

        public EditText getEditText() {
            return mEditText;
        }



        public Rule[] getRules() {
            return mRules;
        }


    }
}

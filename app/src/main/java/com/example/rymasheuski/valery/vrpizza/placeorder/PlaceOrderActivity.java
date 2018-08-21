package com.example.rymasheuski.valery.vrpizza.placeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.rymasheuski.valery.vrpizza.OrderResultActivity;
import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.base.BaseMvpActivity;
import com.example.rymasheuski.valery.vrpizza.component.SpinnerComponent;
import com.example.rymasheuski.valery.vrpizza.util.CartHelper;
import com.example.rymasheuski.valery.vrpizza.util.UiUtil;
import com.example.rymasheuski.valery.vrpizza.util.Validator;

import java.util.List;

public class PlaceOrderActivity extends BaseMvpActivity<PlaceOrderPresenter> implements PlaceOrderContract.MvpView {

    private static final String TAG = PlaceOrderActivity.class.getName();

    private EditText mStreetEditText;
    private EditText mHouseEditText;
    private EditText mFlatEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mDeliveryTimeEditText;
    private EditText mCouponEditText;
    private EditText mCommentEditText;
    private SpinnerComponent mCitySpinner;
    private SpinnerComponent mPaymentSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);

        initView();

        mPresenter = new PlaceOrderPresenter(this);
        mPresenter.onViewIsReady();

    }



    private void initView(){
        UiUtil.prepareToolbar(this, true, R.string.place_order_title);


        mCitySpinner = new SpinnerComponent(this, R.id.spinner_city, R.array.cities_array, R.array.city_id_array);
        mPaymentSpinner = new SpinnerComponent(this, R.id.spinner_payment_type, R.array.payment_type_array, R.array.payment_type_id_array);



        Button sendButton = findViewById(R.id.button_send_order);
        sendButton.setOnClickListener(v -> {
            mPresenter.onSend();

        });


        mStreetEditText = findViewById(R.id.et_street);
        mHouseEditText = findViewById(R.id.et_house);
        mFlatEditText = findViewById(R.id.et_flat);
        mPhoneEditText = findViewById(R.id.et_phone);
        mEmailEditText = findViewById(R.id.et_email);
        mDeliveryTimeEditText = findViewById(R.id.et_delivery_time);
        mCouponEditText = findViewById(R.id.et_coupon);
        mCommentEditText = findViewById(R.id.et_comment);
    }

    @Override
    public void goToOrderResult() {
        Intent intent = new Intent(this, OrderResultActivity.class);
        startActivity(intent);
    }

    @Override
    public Order getFormData() {
        Order order = new Order();
        order.setStreet(getValue(mStreetEditText));
        order.setHouse(getValue(mHouseEditText));
        order.setFlat(getValue(mFlatEditText));
        order.setPhone(getValue(mPhoneEditText));
        order.setEmail(getValue(mEmailEditText));
        order.setDeliveryTime(getValue(mDeliveryTimeEditText));
        order.setCoupon(getValue(mCouponEditText));
        order.setComment(getValue(mCommentEditText));

        order.setCityId(mCitySpinner.getSelectedItemId());
        order.setPaymentTypeId(mPaymentSpinner.getSelectedItemId());

        return order;
    }


    @Override
    public void showErrors(List<Validator.ValidationResult> results) {

        for(Validator.ValidationResult result : results){
            EditText editText = getEditText((PlaceOrderContract.Field) result.getFieldCode());
            switch (result.getRule()){

                case NOT_EMPTY:
                    setEmptyError(editText);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown rule: " + result.getRule() );
            }

        }

    }


    private EditText getEditText(PlaceOrderContract.Field field){
        switch (field){

            case STREET:
                return mStreetEditText;
            case HOUSE:
                return mHouseEditText;
            case FLAT:
                return mFlatEditText;
            case PHONE:
                return mPhoneEditText;
            case EMAIL:
                return mEmailEditText;
            case DELIVERY_TIME:
                return mDeliveryTimeEditText;
                default:
                    throw new IllegalArgumentException("Unknown field: " + field );
        }
    }

     private void setEmptyError(EditText editText){
        CharSequence hint = editText.getHint();

        String error =  getString(R.string.error_is_empty_format, hint);
        editText.setError(error);
    }

    private String getValue(EditText et){
        return String.valueOf(et.getText());
    }
}
